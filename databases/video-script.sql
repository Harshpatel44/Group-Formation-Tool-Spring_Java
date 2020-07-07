select * from passwordPolicy;
select * from passwordPolicyStatus;

update passwordPolicyStatus set MinLength=1;
update passwordPolicyStatus set MinUpperCaseLetter=1;

update passwordPolicyStatus set MinSpecialCharacter=0;
update passwordPolicyStatus set MinUpperCaseLetter=0;





select * from questionManager;
select * from optionManager;

insert into questionResponses value ("B00100100",12,"response1");

select * from questionResponses;