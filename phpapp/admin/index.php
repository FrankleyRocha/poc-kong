<?php
echo "Hello, Admin!\n";

$username = $_SERVER['HTTP_X_CONSUMER_USERNAME'] ?? 'unknown';
echo "User: " . htmlspecialchars($username) . "\n";

$groups = $_SERVER['HTTP_X_CONSUMER_GROUPS'] ?? 'no group';
echo "Groups: " . htmlspecialchars($groups) . "\n";