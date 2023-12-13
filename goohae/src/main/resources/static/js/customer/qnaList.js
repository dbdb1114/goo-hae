let boardSeq;

$('tr[id^="qnaRow"]').click(function () {
    $('html').scrollTop(0);

    $('.modal').show();
    let boardSeq = $(this).attr('id').replace('qnaRow', '');

    let boardPassword = $('#password').val();

    $('.passwordBtn').click(() => {
        if ($('#password').val() == '') {
            boardPassword = null;
        }

        location.href = `/qna-board/detail?boardSeq=${boardSeq}&boardPassword=${boardPassword}`
    })
})

$('.modalCancel').click(function () {
    $('.modal').hide();
})