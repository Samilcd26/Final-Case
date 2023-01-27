const nodemailer=require("nodemailer")
require('dotenv').config({ debug: false })



const sendMail=async(status)=>{
    try{
        const transporter=nodemailer.createTransport({
        host:"smtp.ethereal.email",
        port:process.env.EMAIL_PORT,
        secure:false,
        auth:{
            user: process.env.EMAIL_USER,
            pass: process.env.EMAIL_PASSWORD
        }
    })
    
    await transporter.sendMail({
        from:process.env.EMAIL_USER,
        to:"patikaSamil@test.com",
        subject:`Sayın ${status.firstName} ${status.lastName}.`,
        html:`<p>Keri başvurunuz ${status.creditStatus?"Onaylanmıştır"+` kredi Limitiniz: ${status.creditLimit}`:"maalesef onaylanamadı."}</p>`
    })
    }catch(error){
        console.log(error)
    }
}

sendMail()
module.exports=sendMail