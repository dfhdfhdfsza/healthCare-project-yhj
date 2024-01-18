
let loginBtn = document.getElementById("loginBtn");

// 로그인 버튼 누르면 ID, Password 받아오기
loginBtn.addEventListener("click",()=>{
    let id  = document.getElementById("userID").value;
    let pwd = document.getElementById("userPWD").value;

    let userData = {
        username : id,
        password : pwd
    };


    validateLogin(userData).then(result=>{
        console.log(result+"result<<<<<<<<<<<<<");
    })
});

// id , pwd 검증
async function validateLogin(userData){
    try {
        const url = "/login";
        const config = {
        method:"post",
        header:{
            'content-type':'application/json; charset=utf-8'
        },
        body:JSON.stringify(userData)
    };
    const resp = await fetch(url,config);
    const result = await resp.text();
    return result;
    
    } catch (error) {
        console.log(error);
    }

}