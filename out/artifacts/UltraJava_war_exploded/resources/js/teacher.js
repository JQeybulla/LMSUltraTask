//exporte les données sélectionnées
var $table = $('#table');


$(function () {
    $('#toolbar').find('select').change(function () {
        $table.bootstrapTable('refreshOptions', {
            exportDataType: $(this).val()
        });
    });
})


var trBoldBlue = $("table");


$(trBoldBlue).on("click", "tr", function (){
    $(this).toggleClass("bold-blue");
});


// Ajax for filling Groups Table
$.ajax({
    url: "/UltraJava_war/groups",
    success: function(result){
        console.log(result)
        var myHead = document.getElementById("myHead")
        myHead.innerHTML = ("<tr> \
        <th data-field='state' data-checkbox='true'></th> \
        <th data-field='date' data-filter-control='select' data-sortable='true'>"+"ID"+"</th> \
        <th data-field='examen' data-filter-control='select' data-sortable='true'>"+"Ad"+"</th> \
        <th data-field='examen' data-filter-control='select' data-sortable='true'>"+"Fakulte"+"</th> \
        <th data-field='note' data-sortable='true'>"+"Telebe sayi"+"</th></tr>");
        var myBody = document.getElementById("myBody")
        myBody.innerHTML = ("");
        $.each(result.options, function(key, value){

            $("#table").append(
                "<tr>" +
                    "<td class='bs-checkbox '>" +
                        "<input data-index='0' name='btSelectItem' type='checkbox'>" +
                    "</td>" +
                    "<td>"+value.id+"</td>" +
                    "<td>"+value.name+"</td>" +
                    "<td>"+value.faculty_name+"</td>" +
                    "<td>"+value.student_count+"</td>" +
                "</tr>"
            )
            // console.log(value.subject)
        })

    }
})


$("#groups").click(function() {
    $.ajax({
        url: "/resources/data/groups.json",
        success: function(result){
            // console.log(result)
            var myHead = document.getElementById("myHead")
            myHead.innerHTML = ("<tr> \
            <th data-field='state' data-checkbox='true'></th> \
            <th data-field='date' data-filter-control='select' data-sortable='true'>"+"ID"+"</th> \
            <th data-field='examen' data-filter-control='select' data-sortable='true'>"+"Ad"+"</th> \
            <th data-field='examen' data-filter-control='select' data-sortable='true'>"+"Fakulte"+"</th> \
            <th data-field='note' data-sortable='true'>"+"Telebe sayi"+"</th></tr>");
            var myBody = document.getElementById("myBody")
            var myBody = document.getElementById("myBody")
            myBody.innerHTML = ("");
            $.each(result, function(key, value){

                $("#table").append(
                    "<tr><td class='bs-checkbox '><input data-index='0' name='btSelectItem' type='checkbox'></td><td>"+value.id+"</td><td>"+value.name+"</td><td>"+value.faculty+"</td><td>"+value.students_count+"</td></tr>"
                )
                // console.log(value.subject)
            })

        }
    })
    $("#addExam").hide();
});


$("#examTeacher").click(function() {
    $.ajax({
        url: "/resources/data/exams.json",
        success: function(result){
            // console.log(result)
            var myHead = document.getElementById("myHead")
            myHead.innerHTML = ("<tr> \
            <th data-field='state' data-checkbox='true'></th> \
            <th data-field='date' data-filter-control='select' data-sortable='true'>"+"Fenn"+"</th> \
            <th data-field='examen' data-filter-control='select' data-sortable='true'>"+"Tarix"+"</th> \
            <th data-field='note' data-sortable='true'>"+"Muddet"+"</th></tr>");
            var myBody = document.getElementById("myBody")
            myBody.innerHTML = ("");
            $.each(result, function(key, value){

                $("#table").append(
                    "<tr><td class='bs-checkbox '><input data-index='0' name='btSelectItem' type='checkbox'></td><td>"+value.subject+"</td><td>"+value.date+"</td><td>"+value.duration+"</td></tr>"
                )
                // console.log(value.subject)
            })

        }
    })
    $("#addExam").show();
});


$(document).ready(function(){
    // console.log("Helo")
    $("#table").on("tr", "click", function() {
    });
})


$(document).ready(function() {
    var data; // to store the JSON datacon
    // console.log(data)
    var currentIndex = 0; // to keep track of the current question index

    $.ajax({
        url: '/resources/data/exam.json',
        dataType: 'json',
        success: function(json) {
            data = json; // store the JSON data in the 'data' variable
            console.log('JSON data loaded successfully:', data);
        },
        error: function(jqxhr, textStatus, error) {
            var errorMessage = textStatus + ', ' + error;
            console.error('Error loading JSON data:', errorMessage);
        }
    });

    console.log(currentIndex)
    $('#next').click(function(event) {
        event.preventDefault();
        var question = data[currentIndex % Object.keys(data).length + 1]; // get the next question in order
        console.log(question.answers.A)
        console.log(question.answers.B)
        console.log(question.answers.C)
        console.log(question.answers.D)
        $('#questionHeader').text(question.question); // update the text element with the new question
        $('#answer1').prop('checked', false).next('label').text(question.answers.A);
        $('#answer2').prop('checked', false).next('label').text(question.answers.B);
        $('#answer3').prop('checked', false).next('label').text(question.answers.C);
        $('#answer4').prop('checked', false).next('label').text(question.answers.D);
        $('input[name=answer]:checked').prop('checked', false);
        currentIndex++; // increment the current question index
    });
});


$("#finish").click(function(event){
    event.preventDefault();
    $("#examForm").hide();
    $("#Body").css({
        'content':'',
        'position': 'relative',
        'left': '0',
        'width': '100%',
        'height': '100%',
        'background-color': '#fff',
        'z-index': '9999'
    })
})


// $(document).on("click","#startExamButton",function(){
//     console.log("Helo")
//     $("#Body").css({
//         'content':'',
//         'position': 'absolute',


//         'left': '0',
//         'width': '100%',
//         'height': '100%',
//         'background-color': 'rgba(0, 0, 0, 0.5)',
//         'z-index': '9999'
//     })
//     $("#table").removeClass();
//     $("#table").addClass("table table-borderless");
//     $("#examForm").show();
// })


$("#addExamButton").click(function(){
    $("#addExamForm").css("display", "flex");
})


$("#finishCreateExam").click(function(){
    console.log("Finisn")
    $("#table").append(
        "<tr><td class='bs-checkbox '><input data-index='0' name='btSelectItem' type='checkbox'></td><td>"+"Yeni fenn"+"</td><td>"+"11.11.2024"+"</td><td>"+"2 saat"+"</td></tr>"
    )
    $("#addExamForm").hide();
})


$("#finishCreateExamBtn").click(function(){
    console.log("Finisn")
    $("#table").append(
        "<tr><td class='bs-checkbox '><input data-index='0' name='btSelectItem' type='checkbox'></td><td>"+"Yeni fenn"+"</td><td>"+"11.11.2024"+"</td><td>"+"2 saat"+"</td></tr>"
    )
    $("#addExamForm").hide();
})


$('#removeExam').on('click', function() {

    var confirmed = confirm('Are you sure you want to perform this action?');

    if (confirmed) {
        // perform some action here
        $('table input[type="checkbox"]:checked').each(function() {

            // get the parent tr element of the checkbox
            var tr = $(this).closest('tr');

            // delete the tr element
            tr.remove();
        });
    }
    // loop through all checked checkboxes in the table

});
