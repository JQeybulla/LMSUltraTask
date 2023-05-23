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
                    "<tr><td class='bs-checkbox '><input data-index='0' name='btSelectItem' type='checkbox'></td><td>"+value.subjectName+"</td><td>"+value.examDate+"</td><td>"+"<button class='btn btn-primary' id='startExamButton'>Bashla</button>"+"</td></tr>"
                )
                // console.log(value.subject)
            })
        }
    })
});

// configuring <tr> for Subject names
var subjectsOption =
    "<select style='text-align: center' name=\"subjectCombo\" id=\"subjectCombo\">\n"
var teacherSelectOption =
    "<select name=\"teacherCombo\" id=\"teacherCombo\">\n"

$.ajax({
    url: "/UltraJava_war/subjects-combo",
    success: function (result){
        subjectsOption += ("<option value=" + 0 + ">" + "-------------" + "</option>\n");
        $.each(result.options, function (key, value){
            console.log(value.name)
            subjectsOption += ("<option value=" + value.id + ">" + value.name + "</option>\n");
        });
        subjectsOption += ("</select>")

    }
})
$.ajax({
    url: "/UltraJava_war/subject-teacher-combo",
    success: function (result){
        teacherSelectOption += ("<option value=" + 0 + ">" + "-------------" + "</option>\n");
        $.each(result.options, function (key, value){
            console.log(value.full_name)
            teacherSelectOption += ("<option value=" + value.teacher_id + ">" + value.full_name + "</option>\n");
        });
        teacherSelectOption += ("</select>")

    }
})

// an onClick function for "Subjects"
$("#subjects").click(function() {
    $.ajax({
        url: "/UltraJava_war/subjects",
        success: function(result){
            var myHead = document.getElementById("myHead")
            myHead.innerHTML = ("<tr> \
            <th data-field='state' data-checkbox='true'></th> \
            <th data-field='date' data-filter-control='select' data-sortable='true'>"+"<section>Fennin Adi" +"<br>"+ subjectsOption  +" </section> </th> \
            <th data-field='examen' data-filter-control='select' data-sortable='true'>"+"Muellimin Adi" + "<br>" + teacherSelectOption +"</th> \
            </tr>");
            var myBody = document.getElementById("myBody")
            myBody.innerHTML = ("");


            $.each(result.options, function(key, value){
                $("#table").append(
                    "<tr>" +
                        "<td class='bs-checkbox '>" +
                            "<input data-index='0' name='btSelectItem' type='checkbox'>" +
                        "</td>" +
                        "<td>"+value.name+"</td>" +
                        "<td>"+value.teacher_full_name+"</td>" +
                    "</tr>"
                )
            })
        }
    })
});

var subject_id = 0;
var teacher_id = 0;


// OnChange function for subjects combo
$(document).on("change", "#subjectCombo", function() {
    console.log("This is working")
    var selectedOption = $(this).val(); // Get the selected option value
    subject_id = selectedOption
    sent_url = `/UltraJava_war/subjects-combo-filter?id=${subject_id}&teacher_id=${teacher_id}`
    // corresponding ajax
    $.ajax({
        url: sent_url,
        success: function(result){
            console.log(result)
            var myBody = document.getElementById("myBody")
            myBody.innerHTML = ("");

            $.each(result.options, function(key, value){
                $("#table").append(
                    "<tr>" +
                    "<td class='bs-checkbox '>" +
                    "<input data-index='0' name='btSelectItem' type='checkbox'>" +
                    "</td>" +
                    "<td>"+value.name+"</td>" +
                    "<td>"+value.teacher_full_name+"</td>" +
                    "</tr>"
                )
            })
        }
    })

});

// OnChange function for subjects combo for teacher name
$(document).on("change", "#teacherCombo", function() {
    console.log("This is working")
    var selectedOption = $(this).val(); // Get the selected option value
    teacher_id = selectedOption
    sent_url = `/UltraJava_war/subjects-combo-filter?id=${subject_id}&teacher_id=${teacher_id}`
    console.log(selectedOption)
    // corresponding ajax
    $.ajax({
        url: sent_url,
        success: function(result){
            console.log(result)
            var myBody = document.getElementById("myBody")
            myBody.innerHTML = ("");

            $.each(result.options, function(key, value){
                $("#table").append(
                    "<tr>" +
                    "<td class='bs-checkbox '>" +
                    "<input data-index='0' name='btSelectItem' type='checkbox'>" +
                    "</td>" +
                    "<td>"+value.name+"</td>" +
                    "<td>"+value.teacher_full_name+"</td>" +
                    "</tr>"
                )
            })
        }
    })

});
$(document).ready(function(){
    // console.log("Helo")
    $("#table").on("tr", "click", function() {
    });
})


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

