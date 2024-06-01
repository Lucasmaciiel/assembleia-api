docker compose down

#build api image
docker build -t assembleia-app:latest .

#start environment
docker compose up --build --force-recreate --remove-orphans