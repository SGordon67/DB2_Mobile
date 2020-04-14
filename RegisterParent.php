<?php
if (isset($_POST['signUpButton']))
{
    $email = $_POST['email'];
    $name = $_POST['name'];
    $phone = $_POST['phone'];
    $password = $_POST['password'];
    
    // validate email
    $email = trim(htmlspecialchars($_POST['email']));
    $email = filter_var($email, FILTER_VALIDATE_EMAIL);
    if ($email === false) {
        exit('Invalid Email');
    }
    // validate phone number
    $phone = $_POST['phone'];
    if(!preg_match('/^[0-9]{10}+$/', $phone)) {
        exit('Invalid Phone Number');
    }
        
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
}
?>