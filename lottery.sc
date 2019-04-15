

--!TRACE

start_section :
      task_tickets : array (tasks_range)  of integer;
      task_util : array(tasks_range) of integer;
      nr_tickets : integer;
      nr_tickets := 100;
      to_run : integer;
end section;

priority_section :
    total_inv_util : integer;
    total_inv_util := 0;
    i : integer; 

    for i in tasks_range loop
        if tasks.ready(i)=true
            then
                task_util(i) := (100 * tasks.capacity(i) / tasks.period(i)); 
                total_inv_util := total_inv_util + (100 / task_util(i));
                put(task_util(i));
        end if;  
    end loop;
    put(total_inv_util);

    k : integer;
    k := nr_tickets / total_inv_util;

    local_tickets : integer;
    local_tickets := 0;
    for i in tasks_range loop
        if tasks.ready(i) = true
            then
                task_tickets(i) := 100* k / task_util(i);
                local_tickets := local_tickets + task_tickets(i);
                put(i);
                put(task_tickets(i));
        end if;
    end loop;

    
    random_ticket : random;
    uniform(random_ticket, 0, local_tickets);
    put(random_ticket);
    cur_tickets : integer;
    cur_tickets := 0;
    flag : integer;
    flag := 0;
    for i in tasks_range loop
        if tasks.ready(i) = true
            then
                cur_tickets := cur_tickets + task_tickets(i);
                if (cur_tickets >= random_ticket) and (flag = 0)
                    then 
                         put(cur_tickets);
                         put(cur_tickets >= random_ticket);
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

