-- create db diary;

-- use diary;

-- creating table diary
CREATE TABLE `diary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `diarydate` date NOT NULL,
  `subject` varchar(500) NOT NULL,
  `diarytext` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
)

-- inserting a sample record
insert into Diary (DiaryDate, Subject, DiaryText) 
values(STR_TO_DATE('14-05-2017', '%d-%m-%Y'), 'Hello', 'Ashish');

-- viewing a sample record
select date_format(diarydate, '%d-%m-%Y') from diary;

-- selecting the diary for the selected date
select Subject, diarytext, date_format(diarydate, '%d-%m-%Y') as 'Date' 
from Diary 
where diarydate = STR_TO_DATE('14-05-2017', '%d-%m-%Y');