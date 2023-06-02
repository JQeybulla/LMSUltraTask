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


var archiveSupervisorCombo =
    "<select style='text-align: center' name=\"archiveSupervisorCombo\" id=\"archiveSupervisorCombo\">\n"


// Ajax for filling archiveSupervisorCombo
$.ajax({
    url: "/UltraJava_war/archive?distinctAction=supervisor",
    success: function (result){
        archiveSupervisorCombo += ("<option value=" + 0 + ">" + "-------------" + "</option>\n");
        $.each(result.options, function (key, value){
            console.log(value.name)
            archiveSupervisorCombo += ("<option value=" + value.id + ">" + value.sbj_name + "</option>\n");
        });
        archiveSupervisorCombo += ("</select>")
    }
})
$.ajax({
    url: "/UltraJava_war/archive",
    success: function(result){
        console.log(result);

        var exams = result;

        var tableHead = $("#myHead");
        tableHead.html("<tr> \
                <th data-field='state' data-checkbox='true'></th> \
                <th data-field='date' data-filter-control='select' data-sortable='true'>Superviser <br>" + archiveSupervisorCombo + "</th> \
                <th data-field='date' data-filter-control='select' data-sortable='true'>Tarix</th> \
                <th data-field='examen' data-filter-control='select' data-sortable='true'>Fenn</th> \
                <th data-field='note' data-sortable='true'>Qiymet</th> \
            </tr>");

        var tableBody = $("#table tbody");
        tableBody.empty(); // Clear existing table body content

        $.each(exams.options, function(index, exam){
            tableBody.append(
                "<tr><td class='bs-checkbox'><input data-index='0' name='btSelectItem' type='checkbox'></td><td>"+exam.name+"</td><td>"+exam.exam_date+"</td><td>"+exam.sbj_name+"</td><td>"+exam.score+"</td></tr>"
            );
        });
    },
    error: function(xhr, status, error) {
        console.error(error);
    }
});


// OnChange function for archiveSupervisorCombo in Archive
$(document).on("change", "#archiveSupervisorCombo", function() {
    console.log("This is working")
    var selectedOption = $(this).val(); // Get the selected option value
    teacher_id = selectedOption
    sent_url = `/UltraJava_war/archive?teacher_id=${teacher_id}`
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
                    "<td>"+value.exam_date+"</td>" +
                    "<td>"+value.sbj_name+"</td>" +
                    "<td>"+ value.score +
                    "</td>" +
                    "</tr>"
                )
            })
        }
    })

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
                <th data-field='date' data-filter-control='select' data-sortable='true'>Superviser <br>" + archiveSupervisorCombo + "</th> \
                <th data-field='date' data-filter-control='select' data-sortable='true'>Tarix</th> \
                <th data-field='examen' data-filter-control='select' data-sortable='true'>Fenn</th> \
                <th data-field='note' data-sortable='true'>Qiymet</th> \
            </tr>");

            var tableBody = $("#myBody");
            tableBody.empty();

            $.each(exams.options, function(index, exam){
                console.log(exam)
                tableBody.append(
                    "<tr><td class='bs-checkbox'><input data-index='0' name='btSelectItem' type='checkbox'></td><td>"+exam.name+"</td><td>"+exam.exam_date+"</td><td>"+exam.sbj_name+"</td><td>"+exam.score+"</td></tr>"
                );
            });
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
});


// a combo in exams section, for filtering exams by subject names
var examsSubjectCombo =
    "<select style='text-align: center' name=\"examsSubjectCombo\" id=\"examsSubjectCombo\">\n"


// ajax for filling examsSubjectCombo
$.ajax({
    url: "/UltraJava_war/future-exams-subject-combo",
    success: function (result){
        examsSubjectCombo += ("<option value=" + 0 + ">" + "-------------" + "</option>\n");
        $.each(result.options, function (key, value){
            console.log(value.name)
            examsSubjectCombo += ("<option value=" + value.id + ">" + value.name + "</option>\n");
        });
        examsSubjectCombo += ("</select>")
    }
})


// Onclick function for exams
$("#exams").click(function() {
    $.ajax({
        url: "/UltraJava_war/exams",
        success: function(result){
            var myHead = document.getElementById("myHead")
            myHead.innerHTML = ("<tr> \
            <th data-field='state' data-checkbox='true'></th> \
            <th data-field='date' data-filter-control='select' data-sortable='true'>"+"Fenn <br>"+examsSubjectCombo+"</th> \
            <th data-field='examen' data-filter-control='select' data-sortable='true'>"+"Tarix <br>"+"<input type=\"date\" id=\"examDate\" name=\"examDate\">"+"</th> \
            <th data-field='note' data-sortable='true'>"+"Istirak et"+"</th></tr>");
            var myBody = document.getElementById("myBody")
            myBody.innerHTML = ("");
            $.each(result.options, function(key, value){
                console.log(value.id)
                $("#table").append(
                    "<tr id=\""+value.id + "\"><td class='bs-checkbox '><input data-index='0' name='btSelectItem' type='checkbox'></td><td>"+value.name+"</td><td>"+value.exam_date+"</td><td>"+"<button class='btn btn-primary' id='startExamButton'>Bashla</button>"+"</td></tr>"
                )
                // console.log(value.subject)
            })
        }
    })
});


var exam_date = ""


// OnChange function for exam date combo in Future exams
$(document).on("change", "#examDate", function() {
    console.log("This is working")
    var selectedOption = $(this).val(); // Get the selected option value
    exam_date = selectedOption
    console.log(selectedOption)
    sent_url = `/UltraJava_war/future-exams-subjects-filter?id=${subject_id}&exam_date=${exam_date}`
    //corresponding ajax
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
                    "<td>"+value.exam_date+"</td>" +
                    "<td>"+ "<button class='btn btn-primary' id='startExamButton'>Bashla</button>" +
                    "</td>" +
                    "</tr>"
                )
            })
        }
    })

});


// OnChange function for subjects combo in Future exams
$(document).on("change", "#examsSubjectCombo", function() {
    console.log("This is working")
    var selectedOption = $(this).val(); // Get the selected option value
    subject_id = selectedOption
    sent_url = `/UltraJava_war/future-exams-subjects-filter?id=${subject_id}&exam_date=${exam_date}`
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
                        "<td>"+value.exam_date+"</td>" +
                        "<td>"+ "<button class='btn btn-primary' id='startExamButton'>Bashla</button>" +
                        "</td>" +
                    "</tr>"
                )
            })
        }
    })

});


// configuring <tr> for Subject names
var subjectsOption =
    "<select style='text-align: center' name=\"subjectCombo\" id=\"subjectCombo\">\n"
var teacherSelectOption =
    "<select style='text-align: center' name=\"teacherCombo\" id=\"teacherCombo\">\n"


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


var examAnswers = ""
var examId = ""
$("#finish").click(function(event){

    event.preventDefault();
    var checkedOption = $('input[name="answer"]:checked');
    if (checkedOption.length > 0) {
        var checkedOptionId = checkedOption.attr('id');
        console.log("Answer: " + checkedOptionId);

        var answer = checkedOptionId;
        if (answer === "answer1"){
            answer = "A";
        }else if(answer === "answer2"){
            answer = "B";
        }else if(answer === "answer3"){
            answer = "C";
        }else if (answer === "answer4"){
            answer = "D";
        }


        console.log("Answer: " + checkedOptionId);
        console.log(answer + "----" + correctAnswer)
        if (answer === correctAnswer){
            examAnswers += "1,";
        }else {
            examAnswers += "0,";
        }
    } else {
        console.log("No option selected");
        examAnswers += "0,"
    }
    $.ajax({
        url: `/UltraJava_war/calculate-score?answers=${examAnswers}&exam_id=${examId}`,
        success: function(result){
            console.log(result.options);
        }
    })


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
    examAnswers = "";
})

var questionDetails; // to store the JSON datac
var currentIndex = 0; // to keep track of the current question index

var correctAnswer = ""
$(document).on("click","#startExamButton",function(event){
    $('#next').prop('disabled', false).css('background-color', '#4caf50').css('cursor', 'pointer');
    console.log("Helo")
    var trElementId = event.target.closest("tr").id;
    examId = trElementId
    $.ajax({
        url: `/UltraJava_war/get-exam-questions?exam_id=${trElementId}`,
        success: function(result){
            console.log(result.options)
            questionDetails = result.options;
            console.log('JSON data loaded successfully:', questionDetails);

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

            var question = questionDetails[currentIndex]; // get the next question in order
            $('#questionHeader').text(question.body); // update the text element with the new question
            $('#answer1').prop('checked', false).next('label').text(question.variant_a);
            $('#answer2').prop('checked', false).next('label').text(question.variant_b);
            $('#answer3').prop('checked', false).next('label').text(question.variant_c);
            $('#answer4').prop('checked', false).next('label').text(question.variant_d);
            $('input[name=answer]:checked').prop('checked', false);
            correctAnswer = question.correct_variant;
            currentIndex++; // increment the current question index
            $("#examForm").show();

        },
        error: function(jqxhr, textStatus, error) {
            var errorMessage = textStatus + ', ' + error;
            console.error('Error loading JSON data:', errorMessage);
        }
    })
});

$(document).ready(function() {
    $('#next').click(function(event) {
        event.preventDefault();
        console.log(questionDetails)
        var question = questionDetails[currentIndex]; // get the next question in order


        var checkedOption = $('input[name="answer"]:checked');
        if (checkedOption.length > 0) {
            var checkedOptionId = checkedOption.attr('id');
            console.log("Answer: " + checkedOptionId);
            var answer = checkedOptionId;
            if (answer === "answer1"){
                answer = "A";
            }else if(answer === "answer2"){
                answer = "B";
            }else if(answer === "answer3"){
                answer = "C";
            }else if (answer === "answer4"){
                answer = "D";
            }
            console.log(answer + "----" + correctAnswer)
            if (answer === correctAnswer){
                examAnswers += "1,";
            }else {
                examAnswers += "0,";
            }
        } else {
            console.log("No option selected");
            examAnswers += "0,"
        }

        // console.log("Question: " + question)
        // console.log(question.body)
        // console.log(question.variant_a)
        // console.log(question.variant_b)
        // console.log(question.variant_c)
        // console.log(question.variant_d)
        $('#questionHeader').text(question.body); // update the text element with the new question
        $('#answer1').prop('checked', false).next('label').text(question.variant_a);
        $('#answer2').prop('checked', false).next('label').text(question.variant_b);
        $('#answer3').prop('checked', false).next('label').text(question.variant_c);
        $('#answer4').prop('checked', false).next('label').text(question.variant_d);
        correctAnswer = question.correct_variant;



        $('input[name=answer]:checked').prop('checked', false);
        currentIndex++; // increment the current question index
        console.log("Length: " + questionDetails.length)

        if(currentIndex === questionDetails.length){
            currentIndex = 0;
            $('#next').prop('disabled', true).css('background-color', 'gray').css('cursor', 'default');
        }
        console.log("Current index: " + currentIndex);

    });
});

