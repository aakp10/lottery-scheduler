--!TRACE

start_section :
      task_tickets : array (tasks_range)  of integer;
      nr_tickets : integer;
      nr_tickets := 1000;
      to_run : integer;
      i: integer;
end section;

priority_section :

    local_tickets : integer;
    local_tickets := 0;
    cur_tickets : integer;
    cur_tickets := 0;
    flag : integer;
    flag := 0;
    random_ticket : random;

    for i in tasks_range loop
        if tasks.ready(i) = true
            then
                task_tickets(i) := nr_tickets/ tasks.rest_of_capacity(i);
                local_tickets := local_tickets + task_tickets(i);
                put(i);
                put(task_tickets(i));
        end if;
    end loop;

    uniform(random_ticket, 0, local_tickets);

    for i in tasks_range loop
        if tasks.ready(i) = true
            then
                cur_tickets := cur_tickets + task_tickets(i);
                put(cur_tickets);
                if (cur_tickets >= random_ticket) and (flag = 0)
                    then 
                        to_run := i;
                        flag := 1;
                end if;
        end if;
    end loop;
    put(to_run);
end section;

election_section :
    return to_run;
end section;

