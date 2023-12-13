//
/// GET 요청을 보내는 함수
//function getCartItems() {
//  // AJAX를 사용하여 GET 요청을 보냅니다.
//  $.ajax({
//    url: "/logined-user/mycart", // GET 요청을 보낼 경로
//    method: "GET", // HTTP method
//    success: function(data) {
//      // 요청이 성공하면, 서버에서 전송한 데이터를 처리합니다.
//      console.log(data); // 서버에서 전송한 데이터를 콘솔에 출력합니다.
//      // TODO: 서버에서 전송한 데이터를 이용하여 화면을 업데이트합니다.
//    },
//    error: function(error) {
//      // 요청이 실패하면, 오류 메시지를 출력합니다.
//      console.error(error);
//    }
//  });
//}
//
//// 페이지가 로드되면, GET 요청을 보냅니다.
//$(document).ready(function() {
//  getCartItems();
//});



// 수량 업데이트 함수
function updateCartItemQuantity(input) {
  const itemId = input.getAttribute('data-item-id');
  const itemPrice = input.getAttribute('data-item-price');
  const productEa = input.value;
  const productPrice = itemPrice * productEa;

  // 서버로 업데이트 요청 보내기 (axios를 사용한 예시)
  axios.post('/mycart/update', { itemId, productEa })
    .then(() => {
      // 성공적으로 업데이트되면 합계 가격 업데이트
      const totalEl = input.parentNode.parentNode.nextElementSibling;
      totalEl.textContent = productPrice;
      calculateTotalPrice();
    })
    .catch((error) => {
      console.error(error);
    });
}

// 장바구니에서 선택된 상품 주문 함수
function orderSelectedItems() {
  const checkboxes = document.querySelectorAll('input[name="selected-items"]:checked');
  const itemIds = Array.from(checkboxes).map((checkbox) => checkbox.value);

  // 서버로 주문 요청 보내기
  axios.post('/cartOrder', { itemIds })
    .then(() => {
      // 결제페이지로 이동
      window.location.href = '/payment';
    })
    .catch((error) => {
      console.error(error);
    });
}

// 장바구니 전체 상품 주문 함수
function orderAllItems() {
  const checkboxes = document.querySelectorAll('input[name="selected-items"]');
  const itemIds = Array.from(checkboxes).map((checkbox) => checkbox.value);

  // 서버로 주문 요청
  axios.post('/cartOrder', { itemIds })
    .then(() => {
      // 결제페이지로 이동
      window.location.href = '/checkout';
    })
    .catch((error) => {
      console.error(error);
    });
}

// 합계 가격 계산 함수
function calculateTotalPrice() {
  const checkboxes = document.querySelectorAll('input[name="selected-items"]:checked');
  let totalPrice = 0;

  checkboxes.forEach((checkbox) => {
    const itemRow = checkbox.parentNode.parentNode;
    const productEaInput = itemRow.querySelector('.shoppingCartProductEa');
    const price = quantityInput.getAttribute('data-item-price');
    const productEa = quantityInput.value;
    const itemTotalPrice = price * productEa;
    totalPrice += itemTotalPrice;
  });

  document.querySelector('#totalPrice').textContent = totalPrice;
}