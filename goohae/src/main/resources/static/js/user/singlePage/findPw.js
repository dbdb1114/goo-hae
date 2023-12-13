'use strict';
const
    form = document.querySelector('form'),
    errorMassage = document.querySelectorAll('.errorMassage'),
    // input = document.querySelectorAll('input'),
    fpId = document.querySelector('#fpId'),
    fpName = document.querySelector('#fpName'),
    findPwEmail = document.querySelector('#findPwEmail'),
    findPwEmailSel = document.querySelector('.findPwEmailSel'),
    fpMobile = document.querySelectorAll('.fpMobile'),
    findPwBtn = document.querySelector('.findPwBtn');
    
// --- 미입력 경고
function fpIdCheck() {
    if (fpId.value == "") {
        errorMassage[0].classList.remove('hidden');
        fpId.classList.add('redInputBox');
        return false;
    } else {
        errorMassage[0].classList.add('hidden');
        fpId.classList.remove('redInputBox');
        return true;
    }
}

function fpNameCheck() {
    if (fpName.value == "") {
        errorMassage[1].classList.remove('hidden');
        fpName.classList.add('redInputBox');
        return false;
    } else {
        errorMassage[1].classList.add('hidden');
        fpName.classList.remove('redInputBox');
        return true;
    }
}

function fpMobileCheck() {
    if ((fpMobile[0].value == "")) {
        errorMassage[2].classList.remove('hidden');
        for (let i = 0; i < fpMobile.length; i++) {
            fpMobile[i].classList.add('redInputBox');
        }
        return false;
    } else if (!((fpMobile[0].value.length == "11"))) {
        errorMassage[2].classList.remove('hidden');
        for (let i = 0; i < fpMobile.length; i++) {
            fpMobile[i].classList.add('redInputBox');
        }
        errorMassage[3].classList.remove('nonDisplay');
        return false;
    } else {
        errorMassage[2].classList.add('hidden');
        for (let i = 0; i < fpMobile.length; i++) {
            fpMobile[i].classList.remove('redInputBox');
        }
        return true;
    }
}

//function findPwEmailCheck() {
//    if (findPwEmail.value == "") {
//        errorMassage[3].classList.remove('hidden');
//        findPwEmail.classList.add('redInputBox');
//        findPwEmailSel.classList.add('redInputBox');
//        return false;
//    } else {
//        errorMassage[3].classList.add('hidden');
//        findPwEmail.classList.remove('redInputBox');
//        findPwEmailSel.classList.remove('redInputBox');
//        return true;
//    }
//}

document.addEventListener('keydown', function (e) {
    if (e.keyCode === 13) {
        // e.preventDefault();
        fpIdCheck();
        fpNameCheck();
        fpMobileCheck();
//        findPwEmailCheck();

    };
});

findPwBtn.addEventListener('click', function (e) {
    e.preventDefault();



//    findPwEmailCheck();
//    if (fiNameCheck() && fiEmailCheck() && fiMobileCheck()) {
    if (fpIdCheck()&&fpNameCheck()&&fpMobileCheck() ) {
        form.submit();
    }
});

function validate() {
//    if (fpId.value && fpName.value &&findPwEmail.value && fpMobile[1].value.length == "4"&&fpMobile[2].value.length == "4") {
    if (fpId.value && fpName.value&& fpMobile[0].value.length == "11") {
        findPwBtn.style.cursor = 'pointer';
        findPwBtn.classList.add('buttonLogin');
    } else {
        findPwBtn.classList.remove('buttonLogin');
    }
}

form.addEventListener('keyup', validate);
