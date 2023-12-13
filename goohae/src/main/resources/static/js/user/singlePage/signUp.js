'use strict';
const form=document.querySelector('form');
const signUpId = document.querySelector('.signUpId');
const duppleErrorMsg = document.querySelector('.duppleErrorMsg');
const signUpPw = document.querySelector('.pwInput');
const signUpPwConfirm = document.querySelector('.pwConfirmInput');
const signUpName = document.querySelector('.signUpName');
const signUpPhone = document.querySelector('.signUpPhone');
const signUpAddressNumber = document.querySelector('.signUpAddressNumber');
const signUpAddress = document.querySelector('.signUpAddress');
const signUpButton = document.querySelector('.signUpSubmitBtn');
const checkBox1 = document.querySelector('#emailGet');
const checkBox2 = document.querySelector('#emailNoGet');

const [usingServicePermitionContent, personalInfoPermitionContent] = document.querySelectorAll('.emailGetRadio a'),
    [usingServicepermitionBtn, personalInfoPermitionBtn] = document.querySelectorAll('.emailGetRadio>div>div>button');
console.log(usingServicepermitionBtn)
console.log(personalInfoPermitionBtn)

usingServicePermitionContent.addEventListener('click', function(e){
    e.preventDefault();
    e.target.nextElementSibling.style.display = 'flex'
    e.target.nextElementSibling.classList.add('empasized')
})

personalInfoPermitionContent.addEventListener('click', function(e){
    e.preventDefault();
    e.target.nextElementSibling.style.display = 'flex'
    e.target.nextElementSibling.classList.add('empasized')
})

usingServicepermitionBtn.addEventListener('click',function(e){
    e.preventDefault();
    e.target.parentNode.style.display = 'none'
})

personalInfoPermitionBtn.addEventListener('click', function(e) {
    e.preventDefault();
    e.target.parentNode.style.display = 'none'
})

function signUpIdCheck() {
    const signUpErrorMassage=document.querySelector('.signUpErrorMassage');
    if (signUpId.value == "") {
        signUpErrorMassage.classList.remove('hidden');
        signUpId.classList.add('redInputBox');
        return false;
    } else {
        signUpErrorMassage.classList.add('hidden');
        signUpId.classList.remove('redInputBox');
        return true;
    }
}

function signUpPwCheck() {
    const regExp = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[`~!@#$%^&*|\\\'\";:\/?])[A-Za-z\d`~!@#$%^&*|\\\'\";:\/?]{8,16}$/;
    const signUpPwErrorMassage=document.querySelector('.signUpPwErrorMassage');
        const signUpPwErrorMsg=document.querySelector('.signUpPwErrorMsg');
    if (signUpPw.value == "") {
        signUpPwErrorMassage.classList.remove('nonDisplay');
         signUpPwErrorMsg.classList.add('hidden');
        signUpPw.classList.add('redInputBox');
        return false;
    }else if (regExp.test(signUpPw.value) == true){
        signUpPwErrorMassage.classList.add('nonDisplay');
        signUpPwErrorMsg.classList.add('hidden');
        signUpPw.classList.remove('redInputBox');
        return true;
    } else {
      signUpPwErrorMassage.classList.add('nonDisplay');
        signUpPwErrorMsg.classList.remove('hidden');
        signUpPw.classList.add('redInputBox');
        return false;
    }
}

function signUpPwConfirmCheck() {
    const signUpPwConfirmErrorMassage=document.querySelector('.signUpPwConfirmErrorMassage');
    const signUpPwConfirmErrorMsg=document.querySelector('.signUpPwConfirmErrorMsg');
    if (signUpPwConfirm.value == "") {
        signUpPwConfirmErrorMassage.classList.remove('nonDisplay');
          signUpPwConfirmErrorMsg.classList.add('hidden');
        signUpPwConfirm.classList.add('redInputBox');
        return false;
    }else if (signUpPw.value==signUpPwConfirm.value){
        signUpPwConfirmErrorMassage.classList.add('nonDisplay');
        signUpPwConfirmErrorMsg.classList.add('hidden');
        signUpPwConfirm.classList.remove('redInputBox');
         return true;
    } else {
        signUpPwConfirmErrorMassage.classList.add('nonDisplay');
        signUpPwConfirmErrorMsg.classList.remove('hidden');
        signUpPwConfirm.classList.add('redInputBox');
        return false;
    }
}

function signUpNameCheck() {
    const signUpNameErrorMassage=document.querySelector('.signUpNameErrorMassage');
    if (signUpName.value == "") {
        signUpNameErrorMassage.classList.remove('hidden');
        signUpName.classList.add('redInputBox');
        return false;
    } else {
        signUpNameErrorMassage.classList.add('hidden');
        signUpName.classList.remove('redInputBox');
        return true;
    }
}
function signUpPhoneCheck() {
    const signUpPhoneErrorMassage=document.querySelector('.signUpPhoneErrorMassage');
    if (signUpPhone.value.length>=10 ) {
         signUpPhoneErrorMassage.classList.add('hidden');
             signUpPhone.classList.remove('redInputBox');
             return true;
    } else {
           signUpPhoneErrorMassage.classList.remove('hidden');
                signUpPhone.classList.add('redInputBox');
                return false;
    }
}

function signUpAddressNumberCheck() {
   const signUpAddressNumberErrorMassage=document.querySelector('.signUpAddressNumberErrorMassage');
    if (signUpAddressNumber.value == "") {
        signUpAddressNumberErrorMassage.classList.remove('hidden');
        signUpAddressNumber.classList.add('redInputBox');
        return false;
    } else {
        signUpAddressNumberErrorMassage.classList.add('hidden');
        signUpAddressNumber.classList.remove('redInputBox');
        return true;
    }
}

function signUpAddressCheck() {
   const signUpAddressErrorMassage=document.querySelector('.signUpAddressErrorMassage');
    if (signUpAddress.value == "") {
        signUpAddressErrorMassage.classList.remove('hidden');
        signUpAddress.classList.add('redInputBox');
        return false;
    } else {
        signUpAddressErrorMassage.classList.add('hidden');
        signUpAddress.classList.remove('redInputBox');
        return true;
    }
}

signUpId.addEventListener("focusout",(e)=>{
    $.ajax("/user/join/id-duple-check",{
        type:"post",
        data:{
            id:e.target.value
        },
        success: (data) => {
            console.log(data)
            console.log(duppleErrorMsg)
            if(data == "duplicatedID"){
                duppleErrorMsg.innerHTML="중복 ID";
            } else {
                duppleErrorMsg.innerHTML="";
            }

        }
    })
})

function signUpCheckBox(){
const signUpCheckErrorMassage=document.querySelector('.signUpCheckErrorMassage');
if( checkBox1.checked == true&&
    checkBox2.checked == true){
        signUpCheckErrorMassage.classList.add('hidden');
          return true;
    } else {
        signUpCheckErrorMassage.classList.remove('hidden');
        return false;
    }
}
signUpButton.addEventListener('click', function (e) {
    e.preventDefault();
    signUpIdCheck();
    signUpPwCheck();
    signUpPwConfirmCheck();
    signUpNameCheck();
    signUpPhoneCheck();
    signUpAddressNumberCheck();
    signUpAddressCheck();
    signUpCheckBox();
    if (signUpIdCheck()
    &&signUpNameCheck()&&
    signUpPwCheck()&&
    signUpPwConfirmCheck()&&
    signUpPhoneCheck() &&
      signUpAddressNumberCheck()&&
      signUpAddressCheck()&&
      signUpCheckBox()
      ) {
            form.submit();
            alert(message);
//        console.console("all true");
    }
});
