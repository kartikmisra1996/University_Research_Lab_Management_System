<html>
	<head>
		<title> Employee Modifier </title>
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
$employeeindex= $_GET['index'];

$c = new Controller();
try {
    $c->modifyEmployee($employeeindex, $employeename, $employeejob, $employeerole, $employeesalary);
    $_SESSION["errorEmployeeName"] = "";
} catch (Exception $e) {
    $_SESSION["errorEmployeeName"] = $e->getMessage();
}
?>

<html>
 <head>
 <!-- Redirect to manageEmployees with updated employees -->
        <meta charset="UTF-8">
        <meta http-equiv="refresh" content="1; url=manageEmployees.php">
        <script type="text/javascript">
            window.location.href = "manageEmployees.php"
        </script>
        <title>Page Redirection</title>
    </head>
</html>
