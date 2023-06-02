//exporte les données sélectionnées

var active = "groups";

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


// Onclick for Groups section
$("#groups").click(function() {
    active = "groups";
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
    $("#addExam").hide();
});


$("#examTeacher").click(function() {
    active = "examsForTeacher";
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
    // var currentIndex = 0; // to keep track of the current question index
    // console.log(currentIndex)
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
//
//
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


// add item for groups
$("#addItem").click(function(){
    console.log("ACTIVE: " + active)
    if(active === "groups"){
        $.ajax({
            url: "/UltraJava_war/all-groups-combo",
            success: function(result) {
                var selectElement = $("#facultySelect");

                // Clear existing options
                selectElement.empty();

                $.each(result.options, function(key, value) {
                    // Create a new option element
                    var option = $("<option></option>").attr("value", value.id).text(value.name);

                    // Append the option to the select element
                    selectElement.append(option);
                });
            }
        });
        $("#editModal").show();
    }else if (active === "exams"){
        $("#addExamForm").show();
    }

})


// removing a group from the table (and database)
$('#removeItem').on('click', function() {
    if (active === "groups") {
        url = 'mlmkl';
    } else if (active === "examsForTeacher") {
        url = ";kml;,;l";
    }

    var confirmed = confirm('Are you sure you want to perform this action?');

    if (confirmed) {
        var checkbox = $('table').find('input[name="btSelectItem"]:checked');
        console.log(checkbox)
        if (checkbox.length > 0) {
            var valueIds = "";

            checkbox.each(function() {
                var valueId = $(this).closest('tr').find('td:eq(1)').text();
                valueIds += valueId + ",";
            });

            console.log(valueIds);

            $.ajax({
                url: `/UltraJava_war/delete-group?group_id=${valueIds}`,
                dataType: 'json',
                success: function(json) {
                    data = json; // store the JSON data in the 'data' variable
                    console.log('JSON data loaded successfully:', data);

                    // Remove the row from the table
                    checkbox.closest('tr').remove();
                    console.log('Row deleted successfully.');
                },
                error: function(jqxhr, textStatus, error) {
                    var errorMessage = textStatus + ', ' + error;
                    console.error('Error loading JSON data:', errorMessage);
                }
            });
        } else {
            console.log('No checkbox selected.');
        }
    }
});


// onclick of Edit button
var groupIdtoChange = ""
$("#edit").on("click", function() {
    var checkedCheckbox = $("table").find('input[name="btSelectItem"]:checked');
    if (checkedCheckbox.length > 0) {
        var row = checkedCheckbox.closest("tr");
        var id = row.find("td:eq(1)").text();
        groupIdtoChange = id;
        var name = row.find("td:eq(2)").text();
        var faculty = row.find("td:eq(3)").text();

        $("#nameInput").val(name);
        $("#facultySelect").val(faculty);

        $.ajax({
            url: "/UltraJava_war/all-groups-combo",
            success: function(result) {
                var selectElement = $("#facultySelect");

                // Clear existing options
                selectElement.empty();

                $.each(result.options, function(key, value) {
                    // Create a new option element
                    var option = $("<option></option>").attr("value", value.id).text(value.name);

                    // Append the option to the select element
                    selectElement.append(option);
                });
            }
        });


        // Open the modal
        $("#editModal").show();
    } else {
        console.log("No checkbox selected.");
    }
});


// Modal edit close button click event
$(".close").on("click", function() {
    $("#editModal").hide();
});


// Save button click event
$("#saveChanges").on("click", function() {
    console.log("This is wwwoooorrkkiingg")
    console.log("Group id to change: " + groupIdtoChange)
    var name = $("#nameInput").val();
    var faculty = $("#facultySelect").val();
    console.log(name)
    console.log(faculty)

    let urlToSend = "";
    if (groupIdtoChange === ""){
        urlToSend = `/UltraJava_war/add-group?name=${name}&faculty_id=${faculty}`
    }else {
        urlToSend = `/UltraJava_war/edit-group?id=${groupIdtoChange}&name=${name}&faculty_id=${faculty}`
    }

    // Perform save operation or send data to the server
    $.ajax({
        url: urlToSend,
        success: function(result) {
            console.log(result)
        }
    });

    // Close the modal
    $("#editModal").hide();
});


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


// Onclick function for examsTeacher
$("#examsTeacher").click(function() {
    active = "exams";
    $.ajax({
        url: "/UltraJava_war//exams-by-teacher",
        success: function(result){
            var myHead = document.getElementById("myHead")
            myHead.innerHTML = ("<tr> \
            <th data-field='state' data-checkbox='true'></th> \
            <th data-field='date' data-filter-control='select' data-sortable='true'>"+"Fenn <br>"+examsSubjectCombo+"</th> \
            <th data-field='examen' data-filter-control='select' data-sortable='true'>"+"Tarix <br>"+"<input type=\"date\" id=\"examDate\" name=\"examDate\">"+"</th> \
            </tr>");
            var myBody = document.getElementById("myBody")
            myBody.innerHTML = ("");
            $.each(result.options, function(key, value){

                $("#table").append(
                    "<tr><td class='bs-checkbox '><input data-index='0' name='btSelectItem' type='checkbox'></td><td>"+value.name+"</td><td>"+value.exam_date+"</td></tr>"
                )
                // console.log(value.subject)
            })
        }
    })
});