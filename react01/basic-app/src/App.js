import logo from './logo.svg';
import './App.css';
import CustomButton from './component/CustomButton'

// 데이터 생성 시 보통 const
const ironMan = {
  name: 'Tony Stark',
  heroName: 'Iron-man',
  imgUrl: 'https://cdn.pixabay.com/photo/2020/11/28/03/19/iron-man-5783522_640.png',
  imgSize: 100
}

const weapons = [
  { title: 'Replusor Beam', idx: 1 },
  { title: 'Unibeam Blaster', idx: 2 },
  { title: 'Smart missile', idx: 3 }
];

const listWeapons = weapons.map(weapons =>
  <li key={weapons.idx}>
    {weapons.title}
  </li>
)

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>{ironMan.heroName}</h1>
        <img className='profile'
          src={ironMan.imgUrl}
          alt={ironMan.name + ' 변신 사진'}
          style={{
            width: ironMan.imgSize,
            height: ironMan.imgSize,
            // borderRadius: '50%'
          }} />
        <ul>{listWeapons}</ul>
        <CustomButton />
      </header>
      <div>
      </div>
    </div>
  );
}

export default App;