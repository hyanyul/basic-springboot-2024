import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
// RestAPI -> axios
import axios from "axios";

function Login() {
    // 변수
    const navigete = useNavigate(); // 화면 전호나용 Hook
    const [user, setUser] = useState({
        username: '',
        password: ''
    });

    // 함수
    // function handleChange(e)
    const handleChange = (e) => {
        const { name, value } = e.target;   // username, password 둘 중 하나
        setUser({ ...user, [name]: value });
    }

    // async function handleSubmit(e)
    const handleSubmit = async (e) => {
        e.preventDefault();  // submit동안 다른 이벤트 발생하지 않도록 중지시키는 것

        try {
            const formData = new FormData();
            formData.append('username', user.username);
            formData.append('password', user.password);

            console.log(formData.get('username') + " / " + formData.get('password'));

            // axios 백엔드 호출
            const resp = await axios({
                url: 'http://localhost:8080/api/member/login',  // rest API 호출
                method: 'POST',  // GET, POST, DELETE, PUT
                data: formData,
                withCredentials: true   // 암호화
            });

            console.log(resp);
            if(resp.data.resultCode == 'OK'){
                const { email, mid, role, username } = resp.data.data;
                const transactionTime = resp.data.transactionTime;
                // console.log(email, mid, role, username);
                // localStorage에 저장
                localStorage.setItem("username", username);
                localStorage.setItem("email", email);
                localStorage.setItem("mid", mid);
                localStorage.setItem("role", role);
                localStorage.setItem("loginDt", transactionTime);
                console.log(localStorage);

                alert('로그인 성공');

                navigete("/home", { data : {useData: resp.data.data}}); // 다른 페이지로 데이터 전달

            } else{
                alert('로그인 실패');
            }
        } catch (error) {
            console.log('로그인 에러 : ' + error)
            alert('로그인 실패');
        }
    }

    return (
        <div className="container card form-register"
            style={{ maxWidth: '400px', padding: '1rem' }}>
            <div className="d-flex justify-content-center">
                <div>
                    <div className="my-3 border-bottom">
                        <h3 className="text-start">로그인</h3>
                    </div>
                    <form onSubmit={handleSubmit}>
                        <div className="mb-3 text-start">
                            <label htmlFor="username" className="form-label">아이디</label>
                            <input id="username" type="text" name="username"
                                placeholder="아이디" className="form-control" required
                                value={user.username} onChange={handleChange} />
                        </div>
                        <div className="mb-3 text-start">
                            <label htmlFor="username" className="form-label">비밀번호</label>
                            <input id="password" type="password" name="password"
                                placeholder="비밀번호" className="form-control" required
                                value={user.password} onChange={handleChange} />
                        </div>

                        <button type="submit" className="btn btn-primary me-1">로그인</button>
                        <Link to={'/home'} className="btn btn-secondary">취소</Link>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Login;