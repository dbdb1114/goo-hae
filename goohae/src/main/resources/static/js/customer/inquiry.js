$(document).ready(function () {
    const form = $('#etcForm');
    const btn = $('.go');

    btn.click(function () {
        const formData = new FormData();

        formData.append('title', $('input[name="title"]').val());
        formData.append('content', $('textarea[name="content"]').val());

        if ($('input[name="boardPassword"]').val() == '') {
            formData.append('boardPassword', null);
        } else {
            formData.append('boardPassword', 'input[name="boardPassword"]').val()
        }
        $.ajax({
            url: '/logined-user/qna-board/insert',
            type: 'POST',
            processData: false,
            contentType: false,
            data: formData
        })
            .done((data, status) => {
                console.log(data);
                location.href = '/qna-board/list';
            })
            .fail((err, status) => {
                console.log(err)
            })
        // if ($('input[name="title"]').val() !== "" &&
        //     $('textarea[name="content"]').val() !== "" &&
        //     $('input[name="boardPassword"]').val() == "") {
        //     formData.append('title', $('input[name="title"]').val());
        //     formData.append('content', $('textarea[name="content"]').val());
        //     formData.append('boardPassword', $('input[name="boardPassword"]').val());

        //     $.ajax({
        //         url: '/logined-user/qna-board/insert',
        //         type: 'POST',
        //         processData: false,
        //         contentType: false,
        //         data: formData
        //     })
        //         .done((data, status) => {
        //             location.href = '/qna-board/list';
        //         })
        //         .fail((err, status) => {
        //             console.log(err)
        //         })
        // } else {
        //     return alert('정보가 입력되지 않았습니다.');
        // }
    })
})

