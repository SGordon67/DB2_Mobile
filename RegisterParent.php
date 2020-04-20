<?php
$name = $_POST['name'];
$email = $_POST['email'];
$password = $_POST['password'];
$phone = $_POST['phone'];
$response = array();

// validate email
$email = trim(htmlspecialchars($_POST['email']));
$email = filter_var($email, FILTER_VALIDATE_EMAIL);
$phone = $_POST['phone'];
if ($email === false) {
    $response["success"] = "false";
    echo json_encode($response);
} else if(!preg_match('/^[0-9]{10}+$/', $phone)) {
    $response["success"] = "false";
    echo json_encode($response);
} else{
    $mysqli = new mysqli('localhost', 'root', '', 'DB2');
    $query = "INSERT INTO users(email, password, name, phone) VALUES ('$email','$password','$name','$phone')";
    $mysqli->query($query);

    $query2 = "SELECT id FROM users WHERE email = '$email'";
    $result = $mysqli->query($query2);
    $parentID;
    while ($row = $result->fetch_assoc()) {
        $parentID = $row['id'];
    }
    $query3 = "INSERT INTO parents(parent_id) VALUES ($parentID)";
    $mysqli->query($query3);

    $response["success"] = "true";
    echo json_encode($response);
}
?>