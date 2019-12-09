
<html>
	<head>
		<title> Progress Manager</title>
	<!-- "Add employee" submission form through GET method -->
	</head>
	<body>
		
		<?php
$my_dir = dirname(__FILE__);
require_once $my_dir . '/controller/Controller.php';

session_start();

//Receive employee attributes through GET method
$employeeindex = $_GET['index'];

$pm = new Persistence();
$lab = $pm->loadDataFromStore();

$myEmployee = $lab->getEmployee_index($employeeindex);

for ($i = 0; $i < $myEmployee->numberOfProgressUpdates(); $i++) {
    $myProgress = $myEmployee->getProgressUpdate_index($i);
    echo $myProgress->getDate();
    echo '<br><br>';
    echo "Weekly objective: ";
    echo '<br><br>';
    echo $myProgress->getObjective();
    echo '<br><br><br>';
    echo "Work accomplished: ";
    echo '<br><br>';
    echo $myProgress->getActualWork();
    echo '<br><br><br>';
    
    ?>
    
    <form action="deleteProgress.php" method="get">
			<input type="hidden" name="progressindex" value="<?php echo htmlspecialchars($i); ?>"/>
			<input type="hidden" name="employeeindex" value="<?php echo htmlspecialchars($employeeindex); ?>"/>
			<input type="submit" value="Remove">
			</form>	
			
	<?php
    
    echo '<br><br>';
}

?>

	<form action="progressAdder.php" method="get">
			<input type="hidden" name="index" value="<?php echo htmlspecialchars($employeeindex); ?>"/>
			<input type="submit" value="Add a progress update">
			</form>
			
	</body>