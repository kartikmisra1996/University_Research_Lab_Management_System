<html>
	<head>
		<title> Employee Remover </title>
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
    echo '<br><br>';
}
?>

<html>
	<head>
	<!-- "Delete employee" submission form through GET method -->
	</head>
		<body>
			<form action="deleteEmployee.php" method="get">
				Employee Name:<input type="text" name="name"/>
				<input type="submit" value="submit">
			</form>	
		</body>