
<?php
$name = $_POST['name'];
// validate email
$email = trim(htmlspecialchars($_POST['email']));
$email = filter_var($email, FILTER_VALIDATE_EMAIL);
$password = $_POST['password'];
// validate parent email
$parentEmail = trim(htmlspecialchars($_POST['paEmail']));
$parentEmail = filter_var($parentEmail, FILTER_VALIDATE_EMAIL);
$phone = $_POST['phone'];
$grade = $_POST['grade'];
if ($email === false) {
    $response["success"] = "false";
    echo json_encode($response);
} else if ($email === false) {
    $response["success"] = "false";
    echo json_encode($response);
} else if(!preg_match('/^[0-9]{10}+$/', $phone)) {
    $response["success"] = "false";
    echo json_encode($response);
} else if($grade < 6 || $grade > 12){
    $response["success"] = "false";
    echo json_encode($response);
} else {
    // insert into users table
    $mysqli = new mysqli('localhost', 'root', '', 'DB2');
    $query = "INSERT INTO users(email, password, name, phone) VALUES ('$email','$password','$name','$phone')";
    $mysqli->query($query);

    // get the id that was created for this user
    $query2 = "SELECT id FROM users WHERE email = '$email'";
    $result = $mysqli->query($query2);
    $studentID;
    while ($row = $result->fetch_assoc()) {
        $studentID = $row['id'];
    }

    // get the parents id
    $query3 = "SELECT id FROM users WHERE email = '$parentEmail'";
    $result3 = $mysqli->query($query3);
    $parentID;
    while ($row = $result3->fetch_assoc()) {
        $parentID = $row['id'];
    }

    // insert into students table
    $query4 = "INSERT INTO students(student_id, grade, parent_id) VALUES ($studentID, '$grade', $parentID)";
    $result4 = $mysqli->query($query4);
    if($result4){
        $response["success"] = "true";
        echo json_encode($response);
    } else {
        $query5 = "DELETE FROM users WHERE id = $studentID";
        $result5 = $mysqli->query($query5);
        $response["success"] = "false";
        echo json_encode($response);
    }
}
?>