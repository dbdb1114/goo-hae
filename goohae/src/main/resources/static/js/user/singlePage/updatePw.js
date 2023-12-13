const
    form = document.querySelector('form'),
    newPwInput = document.querySelector('.changePwInput'),
    confirmPwInput = document.querySelector('.changePwConfirm'),
    updatePwBtn = document.querySelector('.updatePwBtn');
console.log(form)
console.log(updatePwBtn)
console.log(newPwInput)
console.log(confirmPwInput)
updatePwBtn.addEventListener('click', function (e) {

    e.preventDefault();

    console.log(newPwInput.value == confirmPwInput.value);
    console.log(newPwInput.value == confirmPwInput.value);
    if (newPwInput.value == confirmPwInput.value && newPwInput.value.length>8 && newPwInput.value.length < 16 ) {
        form.submit();
    }
});

function validate() {
    console.log(newPwInput.value)
    console.log(confirmPwInput.value)
    console.log(newPwInput.value.length)
//    if (fpId.value && fpName.value &&findPwEmail.value && fpMobile[1].value.length == "4"&&fpMobile[2].value.length == "4") {
    if (newPwInput.value == confirmPwInput.value && newPwInput.value.length>8 && newPwInput.value.length < 16) {

        updatePwBtn.style.cursor = 'pointer';
        updatePwBtn.classList.add('buttonLogin');
    } else {
        updatePwBtn.classList.remove('buttonLogin');
    }
}

form.addEventListener('keyup', validate);
