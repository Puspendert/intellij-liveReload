# intellij-liveReload
intellij-liveReload issue with Spring boot devtools

It's a multi module maven project.

Steps:

1. Run EmbeddedRunner.java as a java class, you will notice some errors into the stack, ignore that as I didn't remove the MySQL configurations in order to clone my original project as much as possible.
2. Change somthing in EmbeddedRunner.java, e.g putting `system.out.println()`. On live-reload the changes you will notice that changes are not getting applied.
3. Now restart the project, and you will notice that changes now get applied.
4. Now change something into UserServiceImpl.jav, let devtools reload the project. Once complete you will see an error saying that `bean of type UserService not found.` 
5. restart the project, no error for bean. "Isn't it strange"?
