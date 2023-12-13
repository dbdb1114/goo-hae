document.addEventListener('DOMContentLoaded', function() {
  // 수정 페이지의 폼 요소를 가져오기
  var editForm = document.getElementById('editAddressForm');

  // 폼 요소의 submit 이벤트에 대한 리스너 등록
  editForm.addEventListener('submit', function(event) {
    // 폼의 기본 동작인 페이지 이동 방지
    event.preventDefault();

    // 폼 데이터 가져오기
    var formData = new FormData(editForm);

    // AJAX를 이용한 수정 요청
    var xhr = new XMLHttpRequest();
    xhr.open('PUT', '/address');
    xhr.onreadystatechange = function() {
      if (xhr.readyState === XMLHttpRequest.DONE) {
        if (xhr.status === 200) {
          // 수정 성공 시 처리할 코드 작성
          alert('수정되었습니다.');
          window.location.href = '/address';
        } else {
          // 수정 실패 시 처리할 코드 작성
          alert('수정에 실패하였습니다. 다시 시도해주세요.');
        }
      }
    };
    xhr.send(formData);
  });
});