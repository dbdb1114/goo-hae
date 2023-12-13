'use strict';

const
    form = document.querySelector('form'),
    errorMassage = document.querySelectorAll('.errorMassage'),
    fiName = document.querySelector('#fiName'),
    fiEmail = document.querySelector('#fiEmail'),
    fIEmailDomain = document.querySelector('.fIEmailDomain'),
    fiMobile = document.querySelectorAll('.fiMobile'),
    findIdBtn = document.querySelector('.findIdBtn');

// --- 미입력 경고 함수들
function fiNameCheck() {
    if (fiName.value == "" && fiName.value.length<2) {
        return false;
    } else {
        return true;
    }
}

//function fiEmailCheck() {
//    if (fiEmail.value == "") {
//        errorMassage[1].classList.remove('hidden');
//        fiEmail.classList.add('redInputBox');
//        fIEmailDomain.classList.add('redInputBox');
//        return false;
//    } else {
//        errorMassage[1].classList.add('hidden');
//        fiEmail.classList.remove('redInputBox');
//        fIEmailDomain.classList.remove('redInputBox');
//        return true;
//    }
//}

function fiMobileCheck() {
    if (fiMobile[0].value.length == 11) {
        return true;
    }
}

// 버튼 누를때 각각의 input 조건을 충족하지 못할경우 경고 활성화
// document.addEventListener('keydown', function (e) {
//     if (e.keyCode === 13) {
//         // e.preventDefault();
//         fiNameCheck();
//         fiEmailCheck();
//         fiMobileCheck();
//         errorMassage.classList.remove('hidden');

//         if (fiNameCheck() && fiEmailCheck() && fiMobileCheck()) {
//             form.submit();
//         }
//     };
// });

// 버튼 클릭시 각각의 input 조건을 충족하지 못할경우 경고 활성화
findIdBtn.addEventListener('click', function (e) {
    e.preventDefault();
    if (fiNameCheck() && fiMobileCheck()) {
        form.submit();
    }
});

// 전부 입력시 로그인버튼 색깔 변경
function validate() {
// if (fiName.value && fiEmail.value && fiMobile[1].value.length == 4 && fiMobile[2].value.length == 4) {
    if (fiName.value && fiMobile[1].value.length == 4 && fiMobile[2].value.length == 4) {
        findIdBtn.style.cursor = 'pointer';
        findIdBtn.classList.add('buttonLogin');
    } else {
        findIdBtn.classList.remove('buttonLogin');
    }
}

form.addEventListener('keyup', validate);
