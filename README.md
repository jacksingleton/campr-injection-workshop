# Injection Workshop

## [Getting started as a participant](README-participant.md)

## [Getting started as a facilitator](README-facilitator.md)

## Value

> Injection flaws, such as SQL, OS, and LDAP injection occur when untrusted data
> is sent to an interpreter as part of a command or query. The attacker’s hostile
> data can trick the interpreter into executing unintended commands or accessing
> data without proper authorization.
>
> \- [OWASP Top 10](https://www.owasp.org/index.php/Top_10_2013-Top_10)

After this session, participants will...
* Be able to identify SQL Injection vulnerabilities through blackbox testing
  * How do I know/prove that I'm vulnerable?
  * How bad is a SQL Injection vulnerability?
* Be able to identify injection vulnerabilities in Java through code review
  * How do I identify it while coding?
* Understand the different methodologies around fixing SQL Injection
  * How do I fix SQL injection vulnerabilities?
  * Which technique is good for what?

## Skill Level
Beginner

## Requirements
* Basic knowledge of SQL and relational databases
* Basic knowledge of Java Programming

For hands on sections:
* JDK 1.6+
* Maven 3
* A Java IDE

## Timeframe
* without hands on at a good pace: 30 minutes
* using hands on options: 60-90 minutes

## Tasks
1. Find a way to login as the administrator named “admin” without using their password.
2. Enumerate all the user passwords in the database and identify whether the application uses salts or not.

## Hint
It will be helpful to [download](https://db.apache.org/derby/derby_downloads.html) Derby and use the ij tool to try different sql commands against a live database.
