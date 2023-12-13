'use strict'



// 배송 메모에 관한 이벤트
const selectBtn = document.getElementById('memOrderPostMemo'),
  memoInput = document.getElementById('input_memo');

selectBtn.addEventListener('change', function () {
  if (this.value === "") {
    memoInput.style.visibility = 'visible';
  } else {
    memoInput.style.visibility = 'hidden';
    $('#input_option').val('');
  }
});

memoInput.addEventListener('change', function () {
  $('#input_option').val(this.value);
});


// 결제 체크박스에 대한 이벤트
$('#agree').change(function () {
  if (this.checked) {
    $('#agree1').prop('checked', true);
    $('#agree2').prop('checked', true);
    $('#agree3').prop('checked', true);
  } else {
    $('#agree1').prop('checked', false);
    $('#agree2').prop('checked', false);
    $('#agree3').prop('checked', false);
  }
});



// 주문 정보
const id = document.getElementById('user_id').value,
  receiver = document.getElementById('memPayOrderName').value,  // 이름
  phone = document.getElementById('phoneNum').value,            // 폰
  post = document.getElementById('postNum').value,              // 우편번호
  address = document.getElementById('add_input').value,         // 주소
  memo = document.getElementById('memOrderPostMemo');     // 배송메모

// 상품 정보
const product_code = document.getElementsByClassName('product_code'), // 상품코드들
  product_eas = document.getElementsByClassName('prEa'),              // 상품개수들
  product_prices = document.getElementsByClassName('prPrice'),        // 상품가격들
  product_discounts = document.getElementsByClassName('prDis');     // 상품할인율들



// 주문하기 버튼 클릭 시
const orderBtn = document.getElementsByClassName('order_btn')[0];

orderBtn.addEventListener('click', function () {

  if (memo.value !== '' && $('#agree1').is(':checked') &&
    $('#agree2').is(':checked') && $('#agree3').is(':checked')) {

    $.ajax({
      type: 'post',
      url: '/logined-user/order/insert',
      data: {
        userId: id,
        receiverName: receiver,
        phoneNumber: phone,
        postNumber: post,
        address: address,
        memo: memo.value,
        productCode: product_code[0].value,
        productEa: product_eas[0].innerHTML,
        price: product_prices[0].innerHTML,
        discount: product_discounts[0].innerHTML.split("%")[0]
      },
      success: (res) => {
        if (res.message === 'success') {
          $('#order_seq').val(res.info.orderSeq);
          $('#order_total').val(res.total);
          $('#to_pay_container').css('display', 'block');
        } else {
          alert('주문에 실패했습니다. 확인 후 다시 시도 바랍니다.');
        }
      },
      error: () => {
        alert('주문에 실패했습니다. 확인 후 다시 시도 바랍니다.');
      }
    });

  } else {
    alert('입력을 확인해주세요.');
  }

});