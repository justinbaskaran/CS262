echo "git add *"
git add *

echo "Enter commit message:"
read commit

echo "Commiting.."
git commit -m $commit

echo "Pushing to Origin Master"
git push origin master -f
 
echo "Complete!"

