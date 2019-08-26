package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ExpenseServiceTest {
   
	@Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
		ExpenseService expenseService = new ExpenseService();
        // when
		Project project = new Project(ProjectType.INTERNAL, "projectA");
        // then
    	assertEquals(ExpenseType.INTERNAL_PROJECT_EXPENSE, expenseService.getExpenseCodeByProjectTypeAndName(project));
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
    	ExpenseService expenseService = new ExpenseService();
        // when
    	Project project = new Project(ProjectType.EXTERNAL, "Project A");
    	ExpenseType expenseType = expenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
    	assertEquals(ExpenseType.EXPENSE_TYPE_A, expenseType);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
    	 // given
    	ExpenseService expenseService = new ExpenseService();
        // when
    	Project project = new Project(ProjectType.EXTERNAL, "Project B");
    	ExpenseType expenseType = expenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
    	assertEquals(ExpenseType.EXPENSE_TYPE_B, expenseType);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
    	// given
    	ExpenseService expenseService = new ExpenseService();
        // when
    	Project project = new Project(ProjectType.EXTERNAL, "Project C");
    	ExpenseType expenseType = expenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
    	assertEquals(ExpenseType.OTHER_EXPENSE, expenseType);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
    	// given
    	ExpenseService expenseService = new ExpenseService();
    	UnexpectedProjectTypeException uProjectTypeException = new UnexpectedProjectTypeException("You enter invalid project type");
        // when
    	System.out.println(uProjectTypeException.getMessage());
    	Project project = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE, "Project D");
    	try {
			ExpenseType expenseType = expenseService.getExpenseCodeByProjectTypeAndName(project);
			fail("hhh");
		} catch (UnexpectedProjectTypeException e) {
			// TODO Auto-generated catch block
			assertEquals("You enter invalid project type", uProjectTypeException.getMessage());
		}
    	//System.out.println(expenseType);
        // then
    	
    }
}