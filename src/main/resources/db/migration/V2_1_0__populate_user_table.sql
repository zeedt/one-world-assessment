insert into "user"
(id, title, first_name, last_name, email, mobile_number, password, role, date_registered, verified,date_verified, date_deactivated, status, approval_code, deleted)
 values
 ('70449004-88ed-4914-9a88-a7858a3379a6', 'Mr.', 'John', 'Tina', 'johntena@test.com', '2348100000000', '$2a$10$2QaP.55OvVKy1rJEEyxUaODBPy7cKGLBt3ARMMhW18qc.XBUXLn3q','ADMIN', current_timestamp, true, current_timestamp, null, 'VERIFIED', '0HR321', false),
 ('82f01f7a-11ae-4b23-8c2b-fe64222937e9', 'Prof.', 'Ishola', 'David', 'Isholadavid@test.com', '2348144400000', '$2a$10$yf/jq7Jh/obqQCTUClKwgObv9/0YgMWeS4hFG6KT1qcLtIY2bKb6i','USER', current_timestamp, false , null, null, 'REGISTERED', 'AH6621', false),
 ('348b48ac-b74f-499b-8f76-225fe0d81e72', 'Mr.', 'Kushoro', 'Wales', 'kushorowales@test.com', '4418965380000', '$2a$10$1oiKRbZ1N8KfJrUBp9I9zuXFe0zepV43Ed2DBdCiMW1R0r8xUylK2','ADMIN', current_timestamp, true, current_timestamp, current_timestamp , 'DEACTIVATED', '40FQ12', true);