'use strict'

// 이미지 변경
const main = document.getElementById('main_img');
let imgs = document.getElementsByClassName('product_imgs');


let selected = imgs[0];

imgs[0].addEventListener('click', (e) => {
    main.src = e.target.src;

    selected.classList.remove('selected');
    selected = e.target;

    e.target.classList.add('selected');
});

imgs[1].addEventListener('click', (e) => {
    main.src = e.target.src;

    selected.classList.remove('selected');
    selected = e.target;

    e.target.classList.add('selected');
});

imgs[2].addEventListener('click', (e) => {
    main.src = e.target.src;

    selected.classList.remove('selected');
    selected = e.target;

    e.target.classList.add('selected');
});

imgs[3].addEventListener('click', (e) => {
    main.src = e.target.src;

    selected.classList.remove('selected');
    selected = e.target;

    e.target.classList.add('selected');
});


// 상품 수량 변경
const ea_box = document.getElementById('productEa');
const price_box = document.getElementById('price');
const price = Number(price_box.value);

let ea_btn = document.getElementsByClassName('ea_btn');

ea_btn[0].addEventListener('click', (e) => {
    if (ea_box.value == 1) return;

    let ea = Number(ea_box.value) - 1;

    ea_box.value = ea;
    price_box.value = price * ea;
});

ea_btn[1].addEventListener('click', (e) => {
    let ea = Number(ea_box.value) + 1;

    ea_box.value = ea;
    price_box.value = price * ea;
});


let cart_btn = document.getElementsByClassName('cart_btn')[0];
//const ea_box = document.getElementById('productEa');
//const price_box = document.getElementById('price');
//const price = Number(price_box.value);
const product_code = document.getElementsByClassName('productCode')[0];

cart_btn.addEventListener('click', (e) => {
    $.ajax({
        type: "post",
        url: "logined-user/mycart/insert",
        data: {
            productCode: product_code.value,
            productEa: ea_box.value
        },
        success: (res) => {
            if (res.message === 'success') {
                alert('장바구니에 등록되었습니다.');
            } else {
                alert('확인 후 다시 시도해주세요.');
            }
        },
        error: () => {
            alert('확인 후 다시 시도해주세요.');
        }
    });
})
