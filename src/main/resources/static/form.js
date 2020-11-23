
$(document).ready(function () {
    $('#submit_btn').click(function (e) {
        e.preventDefault()
        let fname = document.querySelector('#firstName').value;
        let lname = document.querySelector('#lastName').value;
        let phonenumber = document.querySelector('#phoneNo').value;

        // Converting JSON data to string 
        var data = JSON.stringify({ fname: fname, lname: lname, phonenumber: phonenumber });


        const url = 'http://localhost:9090/hosteler';
            

        $.ajax({
            type: "POST",
            url: '/hosteler',
            dataType: 'json',
            data: data,
            contentType: 'application/json;charset=UTF-8',
            success: function (msg) {
                console.log('wow' + msg);
            }
        }).done(function (res) {
            console.log(res)
            $(this).text("Submit")
            console.log(res)

        }); 
    })
})