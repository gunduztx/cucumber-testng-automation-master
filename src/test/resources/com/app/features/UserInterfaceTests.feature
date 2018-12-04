Feature: User Interface for SuiteCRM
@Api
Scenario: CRM Name and Modules

#We can add our own preference language#

        Given I logged into suiteCRM
        Then CRM name should be suiteCRM
        And Modules should be displayed
        
 