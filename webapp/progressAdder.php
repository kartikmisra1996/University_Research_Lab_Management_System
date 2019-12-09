
<html>
	<head>
		<title> Progress Update Adder</title>
	<!-- "Add employee" submission form through GET method -->
	</head>
		<body>
<?php $employeeindex = $_GET['index']; ?>

<form action="addProgress.php" method="get">
			Day of month (dd):<input type="text" name="day" maxlength="2"/>
			<?php echo "&nbsp&nbsp&nbsp"; ?>
			Month (mm):<input type="text" name="month" maxlength="2"/>
			<?php echo "&nbsp&nbsp&nbsp"; ?>
			Year (yyyy):<input type="text" name="year" maxlength="4"/>
			<?php echo '<br><br>'; ?>
			Objective:<input type="text" name="objective"
			style="
			       width: 700px;
                   height: 150px;
                   padding: 12px 20px;" />
			<?php echo '<br><br>'; ?>
			Work Done:<input type="text" name="actualWork"
			style="
			       width: 700px;
                   height: 150px;
                   padding: 12px 20px;" />
			<?php echo '<br><br>'; ?>
			<input type="hidden" name="index" value="<?php echo $employeeindex; ?>"/>
			<input type="submit" value="submit">
			</form>
			
	</body>