import React from "react";
import { Link, useNavigate } from 'react-router-dom';

// function Header()와 동일일
const Header = () => {

    const navigate = useNavigate(); // Hook함수는 바로 직접 사용 불가. 변수에 담아 사용해야 함

    function gotoLogin() {
        navigate("/login");
    }

    function logout(){
        // localStorage.setItem("username", "");
        // localStorage.setItem("email", "");
        // localStorage.setItem("mid", "");
        // localStorage.setItem("role", "");
        // localStorage.setItem("loginDt", "");

        localStorage.removeItem('username');
        localStorage.removeItem('email');
        localStorage.removeItem('mid')
        localStorage.removeItem('role');
        localStorage.removeItem('loginDt');

        window.location.reload();   // 현재 페이지 리로드
    }

    // return: 화면 그린다는 뜻
    return (
        <div className="container header">
            <header className="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
                <div className="col-md-1 mb-2 mb-md-0">
                    <a href="/home" className="d-inline-flex link-body-emphasis text-decoration-none">
                        <img src={require('../logo.png')} alt="logo" width={40} />
                    </a>
                </div>

                <ul className="nav col-12 col-md-6 mb-2 justify-content-center">
                    <li><Link to='/home' className="nav-link px-2 link-secondary">홈</Link></li>
                    <li><Link to='/boardList' className="nav-link px-2 link-secondary">게시판</Link></li>
                    <li><Link to='/qnaList' className="nav-link px-2 link-secondary">Q&A</Link></li>
                </ul>

                <div className="col-md text-end me-3">
                    {localStorage.getItem("username") != null ? (
                        <button type="button" className="btn btn-outline-primary"
                            onClick={logout}>로그아웃</button>
                    ) : (
                        <>
                            <button type="button" className="btn btn-outline-primary me-2" onClick={gotoLogin}>로그인</button>
                            <button type="button" className="btn btn-primary">회원가입</button>
                        </>
                    )}
                </div>
            </header>
        </div>
    );  // ; 빠져도 됨, 넣는 거 권장
}

export default Header;