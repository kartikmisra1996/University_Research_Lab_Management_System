
<html>
	<head>
		<title> Employee Modifier</title>
	</head>
	<body>
		
		<?php
$my_dir = dirname(__FILE__);
require_once $my_dir . '/controller/Controller.php';

session_start();

//Receive employee index through GET method
$employeeindex = $_GET['index'];

$pm = new Persistence();
$lab = $pm->loadDataFromStore();

$myEmployee = $lab->getEmployee_index($employeeindex);

?>
	    <!-- "Modify Employee" submission form through GET method -->
		<!-- Default value inside each text field is the original value for the employee attribute -->
		<form action="modifyEmployee.php" method="get">
		Name:<input type="text" name="name" value="<?php echo $myEmployee->getName(); ?>"/>
		Job:<input type="text" name="job" value="<?php echo $myEmployee->getJob(); ?>"/>
		Role:<input type="text" name="role" value="<?php echo $myEmployee->getRole(); ?>"/>
		Salary:<input type="text" name="salary" value="<?php echo $myEmployee->getSalary(); ?>"/>
		<!-- Pass employee index as hidden -->
		<input type="hidden" name="index" value="<?php echo $employeeindex; ?>"/>
		<input type="submit" value="save changes">
</form>