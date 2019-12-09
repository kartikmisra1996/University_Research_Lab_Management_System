<html>
	<head>
		<title> Adding Employee</title>
	</head>
	<body>

<?php
$my_dir = dirname(__FILE__);
require_once $my_dir . '/controller/Controller.php';

session_start();

//Receive employee attributes through GET method
$employeename = $_GET['name'];
$employeejob = $_GET['job'];
$employeerole = $_GET['role'];
$employeesalary = $_GET['salary'];

$c = new Controller();

try {   
    $c->createEmployee($employeename, $employeejob, $employeerole, $employeesalary);
    $_SESSION["errorEmployeeName"] = "";
    //Catch exception for input validation checks
} catch (Exception $e) {
    $_SESSION["errorEmployeeName"] = $e->getMessage();
}
?>

<html>
 <head>
 <!-- Redirect to manageEmployees page with new employee -->
        <meta charset="UTF-8">
        <meta http-equiv="refresh" content="1; url=manageEmployees.php">
        <script type="text/javascript">
            window.location.href = "manageEmployees.php"
        </script>
        <title>Page Redirection</title>
    </head>
</html>



