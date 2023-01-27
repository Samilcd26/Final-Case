import React, { useEffect, useRef, useState } from 'react'
import PhoneInput from 'react-phone-number-input';
import 'react-phone-number-input/style.css'
import { ToastContainer, toast } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

export default function CreateForm(props) {
    const [value, setValue] = useState();
    const [data, setData] = useState({});
    const [getIdData, setGetIdData] = useState();
    let id = useRef();
    let firstName = useRef();
    let lastName = useRef();
    let birthDate = useRef();
    let email = useRef();
    let monthlySalary = useRef();
    let guaranteeAmount = useRef();
    let creditScore = useRef();



    const getData = async () => {
        await fetch(`http://localhost:8080/user/${props.editData}`)
            .then((data) => data.json())
            .then((data) => setGetIdData(data))

        await inputFill()
    }



    const updateData = e => {
        setData({
            ...data,
            [e.target.name]: e.target.value
        })
    }

    const inputFill = () => {
        id.current.value = getIdData.idNumber
        firstName.current.value = getIdData.firstName
        lastName.current.value = getIdData.lastName
        birthDate.current.value = getIdData.birthDate
        email.current.value = getIdData.email
        monthlySalary.current.value = getIdData.monthlySalary
        guaranteeAmount.current.value = getIdData.guaranteeAmount
        creditScore.current.value = getIdData.creditScore
    }
    useEffect(() => {
        if (props.process === "update") {
            getData()




        } else if (props.process === "create") {
            id.current.value = ""
            submit()
        }
    }, [props])


    const update = async () => {

        await setData({
            ...data,
            ['idNumber']: parseInt(id.current.value),
            ['firstName']: (firstName.current.value),
            ['lastName']: (lastName.current.value),
            ['birthDate']: (birthDate.current.value),
            ['email']: (email.current.value),
            ['creditScore']: parseInt(creditScore.current.value),
            ['guaranteeAmount']: parseInt(guaranteeAmount.current.value),
            ['monthlySalary']: parseInt(monthlySalary.current.value),
            ['telNumber']: parseInt(value),
        })
        console.log(data);
        console.log(getIdData);
        await fetch(`http://localhost:8080/user/${data.idNumber}`, {
            method: 'PUT',
            headers: {
                'Accept': 'application.json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(function (res) { notify("success", "Edit successful.") })
            .catch(function (res) { notify("warn", "Edit failed.") })

    }

    const submit = async e => {
        e.preventDefault()
        if (props.process == "update") {
            update()

        } else {
            
            await setData({
                ...data,
            ['idNumber']: parseInt(id.current.value),
            ['firstName']: (firstName.current.value),
            ['lastName']: (lastName.current.value),
            ['birthDate']: (birthDate.current.value),
            ['email']: (email.current.value),
            ['creditScore']: parseInt(creditScore.current.value),
            ['guaranteeAmount']: parseInt(guaranteeAmount.current.value),
            ['monthlySalary']: parseInt(monthlySalary.current.value),
            ['telNumber']: parseInt(value),
            })
            console.log(data);

            await fetch('http://localhost:8080/user', {
                method: 'POST',
                headers: {
                    'Accept': 'application.json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            }).catch(function (res) { notify("warn", "Could not add new customer.") })
            notify("success", "New customer added.")
        }
    }

    const cancelHandle = () => {

        props.formStatus(false)
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
        <div>
            <div className='max-w-3xl text-center border-2 p-20 bg-white rounded-md'>
                <p className='text-4xl pb-12'>Create New User</p>


                <p className='title'>User Info</p>
                <div className='flex py-5 gap-3 justify-center '>

                    <div>
                        <p className='font-bold'>ID</p>
                        <input type='text' name='idNumber' ref={id} pattern="[A-Za-z]{3}" onChange={updateData} />
                    </div>

                    <div>
                        <p className='font-bold'>First Name</p>
                        <input type='text' name='firstName' ref={firstName} onChange={updateData} />
                    </div>

                    <div>
                        <p className='font-bold'>Last Name</p>
                        <input type='text' name='lastName' ref={lastName} onChange={updateData} />
                    </div>


                    <div>
                        <p className='font-bold'>birth Date</p>
                        <input type='date' name='birthDate' ref={birthDate} onChange={updateData} />
                    </div>
                </div>

                <p className='title'>Contact Info</p>
                <div className='flex py-5 gap-3 justify-center'>

                    <div>
                        <p className='font-bold'>Email</p>
                        <input type='email' name='email' ref={email} onChange={updateData} />
                    </div>

                    <div>
                        <p className='font-bold'>Phone</p>
                        <PhoneInput className='input'
                            country="TR"
                            value={value}
                            onChange={setValue} />
                        {/* <input type='tel' name='telNumber' onChange={updateData} /> */}
                    </div>

                </div>

                <p className='title'>Financial Information</p>
                <div className='flex py-5 gap-3 justify-center'>

                    <div>
                        <p className='font-bold'>Monthly Salary</p>
                        <input type='text' name='monthlySalary' ref={monthlySalary} onChange={updateData} />
                    </div>

                    <div>
                        <p className='font-bold'>Credit Score</p>
                        <input type='text' name='creditScore' ref={creditScore} onChange={updateData} />
                    </div>

                    <div>
                        <p className='font-bold'>Guarantee Amount</p>
                        <input type='text' name='guaranteeAmount' ref={guaranteeAmount} onChange={updateData} />
                    </div>
                </div>


                <div className='mt-12'>
                    <ToastContainer />
                    <button className='okButton px-5 w-24 mx-4' onClick={submit} >Save</button>
                    <button className='noButton px-5 w-24 mx-4' onClick={cancelHandle} >Cancel</button>
                </div>

            </div>
        </div>
    )
}
