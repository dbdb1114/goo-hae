$(document).ready(function () {
    const password = $('input[name="password"]');
    const btn = $('#deleteBtn');
    const seq = $('.seq');

    btn.click(() => {

        console.log($('.seq').text());
        $.ajax({
            url: "/logined-user/qna-board/delete",
            type: "post",
            data: {
                boardSeq: $('.seq').text(),
            }
        })
            .then((data, status) => {
                if (status === 'success') {
                    location.href = '/qna-board/list';
                } else {
                    alert('삭제가 되지 않았습니다');
                    location.href = '/customer/main';
                }
            })
            .fail((err, status) => {
                console.log(err);
                console.log(status)
            })
    })
})