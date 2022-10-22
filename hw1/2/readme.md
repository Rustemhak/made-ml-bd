Команда для запуска MR
- Mean: 
`hadoop jar /opt/hadoop-3.2.1/share/hadoop/tools/lib/hadoop-streaming-3.2.1.jar -file /mapper_m.py -mapper "python3 mapper_m.py" -file /reducer_m.py -reducer "python3 reducer_m.py" -input /AB_NYC_2019.csv -output /output_m`
- Var:  `hadoop jar /opt/hadoop-3.2.1/share/hadoop/tools/lib/hadoop-streaming-3.2.1.jar -file /mapper_v.py -mapper "python3 mapper_v.py" -file /reducer_v.py -reducer "python3 reducer_v.py" -input /AB_NYC_2019.csv -output /output_v`