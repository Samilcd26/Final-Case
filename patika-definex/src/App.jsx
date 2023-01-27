import { useEffect, useState } from 'react'
import Home from './components/Home'
import Applications from './components/Applications';

function App() {
  const[btnStatus,setBtnStatus] =useState(true);
  useEffect(()=>{

  },[btnStatus])

  console.log(btnStatus);
  return (
    <div className="flex justify-center">
      <div className='font-mono flex flex-col items-center'>
      <p className='text-5xl'>Patika - DefineX</p>
        <div className='my-5 gap-5 border-2 rounded-full p-1  font-bold'>
          <button className={"mr-2"+btnStatus==true?"activeBtn":"disableBtn"} onClick={()=>setBtnStatus(true)}>Users</button>
          <button className={"ml-2"+btnStatus==true?"disableBtn":"activeBtn"} onClick={()=>setBtnStatus(false)}>Applications</button>
        </div>
        {btnStatus &&<Home/>}
        {btnStatus!=true &&<Applications/>}
      </div>
    </div>
  )
}

export default App
