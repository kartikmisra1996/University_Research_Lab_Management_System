<html>
	<head>
		<title> Staff Manager </title>
	</head>
		<body>
	
<?php 
$my_dir = dirname(__FILE__);
require_once $my_dir . '\model\LaboratoryManager.php';
require_once $my_dir . '\model\Employee.php';
require_once $my_dir . '\persistence\Persistence.php';

$pm = new Persistence();
$lab = $pm->loadDataFromStore();

//Display employees and their attributes
for ($i = 0; $i < $lab->numberOfEmployees(); $i++) {
    $myEmployee = $lab->getEmployee_index($i);
    echo $myEmployee->getName();
    echo "&nbsp&nbsp&nbsp";
    echo $myEmployee->getJob();
    echo "&nbsp&nbsp&nbsp";
    echo $myEmployee->getRole();
    echo "&nbsp&nbsp&nbsp";
    echo $myEmployee->getSalary();
    echo "&nbsp&nbsp&nbsp";
    
    //Close php brackets within the loop to use html GET form submissions
    ?>
    <form action="employeeModifier.php" method="get">
			<input type="hidden" name="index" value="<?php echo htmlspecialchars($i); ?>"/>
			<input type="submit" value="Edit">
			</form>
	<form action="progressManager.php" method="get">
			<input type="hidden" name="index" value="<?php echo htmlspecialchars($i); ?>"/>
			<input type="submit" value="View progress updates">
			</form>
	<?php
	//Reopen php brackets before the end of the loop to iterate through next loop 
    echo '<br><br>';
}
?>
	    <!-- "Add Employee" submission form through GET method -->
		<form action="addEmployee.php" method="get">
			Name:<input type="text" name="name"/>
			Job:<input type="text" name="job"/>
			Role:<input type="text" name="role"/>
			Salary:<input type="text" name="salary"/>
			<input type="submit" value="submit">
			</form>
		 <!-- Link to Remove Employee page -->
			<a href="employeeRemover.php">Delete an employee</a><br>
	</body>