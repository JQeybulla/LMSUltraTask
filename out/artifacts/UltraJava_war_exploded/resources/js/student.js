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
$.ajax({
    url: "/UltraJava_war/archive",
    success: function(result){
        console.log(result);

        var exams = result;

        var tableBody = $("#table tbody");
        tableBody.empty(); // Clear existing table body content

        $.each(exams, function(index, exam){
            tableBody.append(
                "<tr><td class='bs-checkbox'><input data-index='0' name='btSelectItem' type='checkbox'></td><td>"+exam.supervisorName+"</td><td>"+exam.examDate+"</td><td>"+exam.subjectName+"</td><td>"+exam.score+"</td></tr>"
            );
        });
    },
    error: function(xhr, status, error) {
        console.error(error);
    }
});

$("#archive").click(function() {
    $.ajax({
        url: "/UltraJava_war/archive",
        success: function(result){
            console.log(result);

            var exams = result;

            var tableHead = $("#myHead");
            tableHead.html("<tr> \
                <th data-field='state' data-checkbox='true'></th> \
                <th data-field='date' data-filter-control='select' data-sortable='true'>Superviser</th> \
                <th data-field='date' data-filter-control='select' data-sortable='true'>Tarix</th> \
                <th data-field='examen' data-filter-control='select' data-sortable='true'>Fenn</th> \
                <th data-field='note' data-sortable='true'>Qiymet</th> \
            </tr>");

            var tableBody = $("#myBody");
            tableBody.empty();

            $.each(exams, function(index, exam){
                tableBody.append(
                    "<tr><td class='bs-checkbox'><input data-index='0' name='btSelectItem' type='checkbox'></td><td>"+exam.supervisorName+"</td><td>"+exam.examDate+"</td><td>"+exam.subjectName+"</td><td>"+exam.score+"</td></tr>"
                );
            });
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
});

$("#exams").click(function() {
    $.ajax({
        url: "/UltraJava_war/exams",
        success: function(result){
            var myHead = document.getElementById("myHead")
            myHead.innerHTML = ("<tr> \
            <th data-field='state' data-checkbox='true'></th> \
            <th data-field='date' data-filter-control='select' data-sortable='true'>"+"Fenn"+"</th> \
            <th data-field='examen' data-filter-control='select' data-sortable='true'>"+"Tarix"+"</th> \
            <th data-field='note' data-sortable='true'>"+"Istirak et"+"</th></tr>");
            var myBody = document.getElementById("myBody")
            myBody.innerHTML = ("");
            $.each(result, function(key, value){

                $("#table").append(
                    "<tr><td class='bs-checkbox '><input data-index='0' name='btSelectItem' type='checkbox'></td><td>"+value.subject_id+"</td><td>"+value.exam_date+"</td><td>"+"<button class='btn btn-primary' id='startExamButton'>Bashla</button>"+"</td></tr>"
                )
                // console.log(value.subject)
            })

        }
    })
});
$(document).ready(function(){
    // console.log("Helo")
    $("#table").on("tr", "click", function() {
    });
})
// $(document).ready(function() {
//     var data; // to store the JSON datacon
//     // console.log(data)
//     var currentIndex = 0; // to keep track of the current question index
//
//     $.ajax({
//         url: '/resources/data/exam.json',
//         dataType: 'json',
//         success: function(json) {
//             data = json; // store the JSON data in the 'data' variable
//             console.log('JSON data loaded successfully:', data);
//         },
//         error: function(jqxhr, textStatus, error) {
//             var errorMessage = textStatus + ', ' + error;
//             console.error('Error loading JSON data:', errorMessage);
//         }
//     });
//
//     console.log(currentIndex)
//     $('#next').click(function(event) {
//         event.preventDefault();
//         var question = data[currentIndex % Object.keys(data).length + 1]; // get the next question in order
//         console.log(question.answers.A)
//         console.log(question.answers.B)
//         console.log(question.answers.C)
//         console.log(question.answers.D)
//         $('#questionHeader').text(question.question); // update the text element with the new question
//         $('#answer1').prop('checked', false).next('label').text(question.answers.A);
//         $('#answer2').prop('checked', false).next('label').text(question.answers.B);
//         $('#answer3').prop('checked', false).next('label').text(question.answers.C);
//         $('#answer4').prop('checked', false).next('label').text(question.answers.D);
//         $('input[name=answer]:checked').prop('checked', false);
//         currentIndex++; // increment the current question index
//     });
// });


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

$(document).on("click","#startExamButton",function(){
    console.log("Helo")
    $("#Body").css({
        'content':'',
        'position': 'absolute',

        'left': '0',
        'width': '100%',
        'height': '100%',
        'background-color': 'rgba(0, 0, 0, 0.5)',
        'z-index': '9999'
    })
    $("#table").removeClass();
    $("#table").addClass("table table-borderless");
    $("#examForm").show();
})

