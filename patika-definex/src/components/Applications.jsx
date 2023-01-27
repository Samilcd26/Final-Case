import React, { useEffect, useRef, useState } from 'react'
import Info from './Info';
import { ToastContainer, toast } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

export default function Applications() {
  const [hiddett, setHiddet] = useState(false);
  const [propData, setPropData] = useState([]);
  const [inputData, setInputData] = useState({})
  const [data, setData] = useState([]);
  let createForm = useRef();



    useEffect(() => {
      hiddett == true ? createForm.current.className = "absolute " :createForm.current.className = "absolute hidden"
    }, [hiddett])

  const updateData = e => {
    setInputData({
      ...inputData,
      [e.target.name]: e.target.value
    })

  }

  const getData = (id) => {
    fetch(`http://localhost:8080/user/${id}`)
      .then((data) => data.json())
      .then((data) => setData(data))
      .catch((err) =>{console.log("hata")})

  }

  const notify = (str, message) => {
    switch (str) {
        case 'info':
            
            toast.info(message, {
                position: "bottom-right",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "colored",
            });
            break

        case 'success':
            
            toast.success(message, {
                position: "bottom-right",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "colored",
            });
            break

        case "warn":

            toast.warn(message, {
                position: "bottom-right",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "colored",
            });
            break

        case "error":

            toast.error(message, {
                position: "bottom-right",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "colored",
            });
            break

        default:
            toast(message, {
                position: "bottom-right",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "colored",
            });
            break
    }
}

  const sendCredi = async () => {

    setInputData({
      ...inputData,
      ['idNumber']: parseInt(inputData.idNumber),
    })

    getData(inputData.idNumber)
    
    const rawResponse = await fetch('http://localhost:8080/queue', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(inputData)
    });
    const content = await rawResponse.json();
    setPropData(content)

    setHiddet(true)
  }

  return (
    <div>
      <div className='flex flex-col py-12 px-10 gap-8 justify-center border-2 items-center'>

        <div>
          <p className='font-bold'>ID</p>
          <input type='text' name='idNumber' pattern="[A-Za-z]{3}" onChange={updateData} />
        </div>
        <div>
          <p className='font-bold'>Birth Date</p>
          <input type='date' name='birthDate' onChange={updateData} />
        </div>

        <button className='activeBtn ' onClick={() => sendCredi()}>Sed</button>


        <div className="absolute hidden" ref={createForm}>
          <Info dataa={propData} userData={data} setHiddet={setHiddet} />
        </div>

      </div>
    </div>
  )
}
