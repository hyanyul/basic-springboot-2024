import axios from 'axios';  // REST API 호출 핵심

// Hook 함수 사용
import React, { useState, useEffect } from 'react';

// 공통 함수 추가
import * as common from '../common/CommonFunc';

// Navigation
import { Link } from 'react-router-dom';

function BoardList() {   // 객체를 만드는 함수
    // 변수 선언, return 또는 render() html 또는 react 태그에서 반복될 때 만듦
    const [boardList, setBoardList] = useState([]); // 배열값을 받아서 상태를 저장하기 때문에 []
    const [pageList, setPageList] = useState([]);   // 페이징을 위한 배열 데이터
    const [nextBlock, setNextBlock] = useState(0);  // 다음 블럭 값
    const [prevBlock, setPrevBlock] = useState(0);  // 이전 블럭 값
    const [totalPageNum, setTotalPageNum] = useState(0);    // 마지막 페이지 번호

    // 함수 선언
    // 제일 중요
    const getBoardList = async (page) => {
        var pageString = (page == null) ? 'page=1' : 'page=' + page;

        try {   // 백엔드 서버가 실행되지 않으면 예외 발생(AXIOS ERROR)
            const resp = (await axios.get("//localhost:8080/api/board/list/free?" + pageString));

            const resultCode = resp.data.resultCode;
            console.log(resultCode);    // OK 또는 ERROR

            if (resultCode == 'OK') {
                setBoardList(resp.data.data); // boardList 변수에 담는 작업
                const paging = resp.data.paging;
                console.log(resp.data.data);    // 페이징 정보
                // console.log(paging); // 개발 완료 시 콘솔 로그 주석처리

                const { endPage, nextBlock, page, prevBlock, startPage, totalListSize, totalPageNum } = paging;
                // console.log(totalListSize);
                // console.log(totalPageNum);

                const tempPages = [];
                for (let i = startPage; i <= endPage; i++) {
                    tempPages.push(i); // [1.2.3.4.5]....
                }

                setPageList(tempPages);
                setNextBlock(nextBlock);
                setPrevBlock(prevBlock);
                setTotalPageNum(totalPageNum);  // 마지막 페이지 번호

            } else {
                alert("문제 발생");
            }

        } catch (error) {
            console.log(">>>>> " + error);

            alert("서버가 연결되지 않았습니다. 관리자에게 문의하세요.")
        }

    }

    function onPageClick(page) {
        console.log(page);
        getBoardList(page);
    }

    useEffect(() => {
        getBoardList();
    }, []);

    return (
        <div className="container">
            <table className='table table-hover'>
                <thead className='table-dark'>
                    <tr className='text-center'>
                        <th>번호</th>
                        <th style={{ width: '50%' }}>제목</th>
                        <th>글쓴이</th>
                        <th>조회수</th>
                        <th>작성일시</th>
                    </tr>
                </thead>
                <tbody>
                    {/* 반복으로 들어갈 부분 */}
                    {boardList.map((board) => (
                        <tr className='text-center' key={board.bno}>
                            <td>{board.num}</td>
                            <td className='text-start'>
                                <Link to={`/boardDetail/${board.bno}`}>
                                    {board.title}
                                </Link>
                                {
                                    // console.log(board.replyList != null ? board.replyList.length : 0)
                                    board.replyList != null &&
                                    <span className='badge text-bg-warning ms-2'>{board.replyList.length}</span>
                                }
                            </td>
                            <td>{board.writer}</td>
                            <td>{board.hit}</td>
                            <td>{common.formatDate(board.createDate)}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            {/* 페이징 처리 */}
            <div className='d-flex justify-content-center'>
                <nav aria-label='Page navigation'>
                    <ul className='pagination'>
                        <li className='page-item'>
                            <button className='page-link' aria-label='first' onClick={() => onPageClick(1)}>
                                <span>⟪</span>
                            </button>
                        </li>
                        <li className='page-item'>
                            <button className='page-link' aria-label='Previous' onClick={() => onPageClick(prevBlock)}>
                                <span>⟨</span>
                            </button>
                        </li>
                        {pageList.map((page, index) => (
                            <li className='page-item' key={index}>
                                <button className='page-link' onClick={() => onPageClick(page)}>
                                    {page}
                                </button>
                            </li>
                        ))}
                        <li className='page-item'>
                            <button className='page-link' aria-label='next' onClick={() => onPageClick(nextBlock)}>
                                <span>⟩</span>
                            </button>
                        </li>
                        <li className='page-item'>
                            <button className='page-link' aria-label='last' onClick={() => onPageClick(totalPageNum)}>
                                <span>⟫</span>
                            </button>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    );
}

export default BoardList;