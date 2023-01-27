import React, { useEffect, useState } from 'react'

export default function Info(props) {
 

  
  
  useEffect(() => { }, [props])
  
  
    return (
      <div className='text-center border-2 p-20 bg-white rounded-md relative '>
        <button className='absolute right-3 top-3 rounded-full bg-red-500 w-7 h-7 font-extrabold text-white hover:bg-red-400' onClick={() => props.setHiddet(false)}>X</button>
        <p className='text-2xl mb-10 border-b-2'>Başvurular</p>
    
        {props.dataa.length!=0 &&<div>
          <p>Adı:{props.userData.firstName}</p>
         <p>Soyadı:{props.userData.lastName}</p>
          <p>Credit Limiti:{props.userData.creditLimit}</p>
          <p>Başvuru sonucu:{props.userData.creditStatus ? "Confirm" : "Unconfirmed"}</p> 
        </div>}
        
  
  
  
      </div>
    )
 
}
