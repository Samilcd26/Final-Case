import React, { useEffect, useRef, useState } from 'react'
import CreateForm from './CreateForm';
import { ToastContainer, toast } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';


export default function Home() {
    const [data, setData] = useState([]);
    const [selected, setSelected] = useState();
    const [key, setKey] = useState();
    const [createFormStatus, setcreateFormStatus] = useState();
    const [process, setprocess] = useState("");


    let createForm = useRef();

    useEffect(() => {

        getAllData()
    }, [])

    useEffect(() => {
        createFormStatus == false ? createForm.current.className = "absolute top-0 -left-20 hidden" : "absolute top-0 -left-20"
        getAllData()
    }, [createFormStatus])

    const onSelect = (event) => {
        const selectedIndex = event.target.options.selectedIndex;
        const selectedKey = parseInt(event.target.options[selectedIndex].getAttribute('data-key'))
        setSelected(selectedIndex)
        setKey(selectedKey)

    }

    const getAllData = () => {
        fetch('http://localhost:8080/user')
            .then((data) => data.json())
            .then((data) => setData(data))
    }

    const sendEmail =async (usr) => {
        console.log(usr);
        await fetch('http://localhost:8080/queue/create', {
            method: 'POST',
            headers: {
                'Accept': 'application.json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(usr)
        }).catch(function (res) {notify("warn", "Could not add new customer.") })
            notify("success", "New customer added.")
       
        notify("success", "Please check your email.")
    }

    const editHandle = async (create) => {

        switch (selected) {
            case 1:
                console.log(key);
                // *!DELETE
                await fetch(`http://localhost:8080/user/${key}`, {
                    method: 'DELETE'
                }).then(function (res) { notify("success", "Deletion successful.") })
                    .catch(err => notify("warn", err) )
                getAllData()
                
                break
            case 2:
               //*update
                setprocess("update")
                setcreateFormStatus(true)
                createForm.current.className = "absolute top-0 -left-20"
                break

        }

        if (create == 'create') {

            setprocess("create")
            setcreateFormStatus(true)
            createForm.current.className = "absolute top-0 -left-20"
        }

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


    return (
        <div className='text-center relative'>
            <div>

                <table  >
                    <thead >
                        <tr>
                            <th>ID</th>
                            <th>USER INFO</th>
                            <th>CONTACT</th>
                            <th>FINANCIAL INFORMATION</th>
                            <th>CREDIT STATUS</th>
                            <th><button className='bg-green-500 rounded-full  py-2 px-3 text-sm text-green-200 hover:text-green-500 hover:bg-green-200' onClick={() => editHandle("create")}>CREATE USER</button></th>
                        </tr>
                    </thead>
                    <tbody>
                        {data.length !== 0 && data.map(user =>
                            <tr>
                                <td>{user.idNumber}</td>
                                <td>
                                    <p>First Name: {user.firstName}</p>
                                    <p>Last Name: {user.lastName}</p>
                                    <p>Birth Date: {user.birthDate}</p>
                                </td>
                                <td>
                                    <p>Tel Number: {user.telNumber}</p>
                                    <p>E-Mail: {user.email}</p>
                                </td>

                                <td>
                                    <p>Monthly Salary: {user.monthlySalary}</p>
                                    <p>Credit Score: {user.creditScore}</p>
                                    <p>Guarantee Amount: {user.guaranteeAmount} </p>
                                </td>
                                <td>
                                   
                                    <p>Credit Limit: {user.creditLimit}</p>
                                    {user.creditStatus ? <p className='okButton'>OK</p> : <p className='noButton'>No</p>}
                                </td>

                                <td>
                                    {user.creditStatus ? <button className='mb-2 w-64 py-1 border-green-500 rounded-full border-[1px] hover:bg-green-500 hover:text-white' onClick={() => sendEmail(user)} >Apply</button> : <div className='' />}
                                    <ToastContainer />
                                    <div className='flex'>
                                        <select className='cBox' onClick={onSelect} >
                                            <option name='delete' data-key={user.idNumber} value={user.idNumber} className='bg-sky-500 text-white'> - </option>
                                            <option name='delete' data-key={user.idNumber} value={user.idNumber} className='bg-red-500 text-red-200'>Delete</option>
                                            <option name='update' data-key={user.idNumber} className='bg-[#EFAD4D] text-[#3e2e17] text'>Update</option>
                                        </select>
                                        <button className='ml-2 w-32 py-2 border-green-500 rounded-full border-[1px] hover:bg-green-500 hover:text-white' onClick={editHandle}>✔️</button>

                                    </div>
                                </td>

                            </tr>
                        )}
                    </tbody>
                </table>
            </div>


            <div className='absolute top-0 -left-20 hidden' ref={createForm}>
                <div className='  top-0 -left-20 w-screen h-screen backdrop-blur-sm bg-white/30  ' />
                <div className='absolute left-1/3 top-8 z-30 '>

                    <CreateForm formStatus={setcreateFormStatus} editData={key} process={process} />

                </div>

            </div>

        </div>
    )
}
