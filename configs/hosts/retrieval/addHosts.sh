echo "#Custom adding" >> /etc/hosts
echo "$(route -n | awk '/UG[ \t]/{print $2}')       $IMS_URL1" >> /etc/hosts
echo "$(route -n | awk '/UG[ \t]/{print $2}')       $IMS_URL2" >> /etc/hosts