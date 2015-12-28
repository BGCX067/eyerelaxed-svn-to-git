echo "Running application ..."
cd ~
if [ -f screenapp.lock ]
then
echo lock exists!
exit 1
else
touch screenapp.lock
java Scrin

rm -f screenapp.lock
fi
