import './App.css';

import 'bootstrap/dist/css/bootstrap.min.css';

// 화면 라우팅을 위해 라이브러리 추가
import { Routes, Route } from 'react-router-dom';
import React from 'react';

// 만든 화면 추가
import Home from './routes/Home';
import BoardList from './routes/BoardList';
import QnaList from './routes/QnaList';
import Login from './routes/Login';
import BoardDetail from './routes/BoardDetail';

function App() {
  return (  // return 밑에는 무조건 하나 이상의 태그 필요
    <Routes>
      <Route path='/home' element={<Home/>}/>
      <Route path='/boardList' element={<BoardList/>}/>
      <Route path='/boardDetail/:bno' element={<BoardDetail/>}/>
      <Route path='/qnaList' element={<QnaList/>}/>
      <Route path='/login' element={<Login/>}/>
    </Routes>
  );
}

export default App;
