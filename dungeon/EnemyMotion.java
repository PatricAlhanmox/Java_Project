package unsw.dungeon;

public class EnemyMotion {


    
    public void moveSystem( Enemy e) {
       
        if (e.get_invinMove() == 0 && e.freeze == 0) {
            
            towards(e);
        }else if(e.freeze > 0)
        {
            
            e.freeze -= 1; 
        } else {
            
             leaving(e);
        }
    }

    public void towards( Enemy e)
    {  
        
        if(e.getX() != e.get_playerX())
        {
            System.out.println("ente enemy location x is " + e.getX() + " y is "+e.getY());
            int x_direction = e.getX() - e.get_playerX();
            if(x_direction > 0)
            {
                x_direction = 1;
            }else{
                x_direction = -1;
            }
            int new_x = e.getX() - x_direction;
            if(e.enemyMovable(new_x, e.getY())) //  if x is diff ,move in x best direction 
            {
                System.out.println("enter222");
                e.x().set(new_x);
                System.out.println("ente enemy new location  x is " + e.getX() + " y is "+e.getY());
                return;
            }else if(e.getY() != e.get_playerY()) // if x direction is not moveable, but y direction also diff, move in the best y direction
            {
                int y_direction = e.getY() - e.get_playerY();
                if(y_direction > 0)
                {
                    y_direction = 1;
                }else{
                    y_direction = -1;
                }
                int new_y = e.getY() - y_direction;
                if(e.enemyMovable(e.getX(),new_y )) // if best y direction is movebele
                {
                    e.y().set(new_y);
                    return;
                }
                // if function is not terminate , means must move in y direction
                int bad_y = e.getY() + y_direction;
                if(e.enemyMovable(e.getX(),bad_y )) // if bad y direction is movebele
                {
                    e.y().set(bad_y);
                    return;
                }


            }
            //if function is not terminated , means must move in bad x direction
            int bad_x = e.getX()+x_direction;
            if(e.enemyMovable(bad_x,e.getY() )) // if bad y direction is movebele
            {
                e.x().set(bad_x);
                return;
            }
            
        }else if( e.getY() != e.get_playerY()) // if y is diff, try move in best y
        {
            
            int y_direction = e.getY() - e.get_playerY();
            if(y_direction > 0)
            {
                y_direction = 1;
            }else{
                y_direction = -1;
            }
            int new_y = e.getY() - y_direction;
            // System.out.println("e y is "+e.getY()+" player y is "+e.get_playerY()+" y_direction is "+y_direction+" new_y is "+ new_y+"\n");
            if(e.enemyMovable(e.getX(),new_y )) // if best y direction is movebele
            {
                // System.out.print("here\n");
                
                e.y().set(new_y);
                return;
                
            }else if( e.getX() != e.get_playerX()) // if best y is not moveable , and x is diff, move in best x 
            {
                int x_direction = e.getX() - e.get_playerX();
                if(x_direction > 0)
                {
                    x_direction = 1;
                }else{
                    x_direction = -1;
                }
                int new_x = e.getX()-x_direction;
                if(e.enemyMovable(new_x, e.getY())) // if best x is moveable
                {
                    // e.setX(new_x);
                    e.x().set(new_x);
                    return;
                }

                // if best x is not moveable , move in bad x
                int bad_x =e.getX() + x_direction;
                if(e.enemyMovable(bad_x, e.getY()))
                {
                    // e.setX(bad_x);
                    e.x().set(bad_x);
                    return;
                }



            }
            //if function is not terminated must move in bad y
            int bad_y = e.getY() + y_direction;
            if(e.enemyMovable(e.getX(), bad_y))
            {
                // e.setY(bad_y);
                e.y().set(bad_y);
            }


        }
    }

    public void leaving( Enemy e)
    {  
        System.out.println("enemyxY at" + e.getX() +e.getY() +" playerxy at " + e.get_playerX()+e.get_playerY());
        if(e.getX() != e.get_playerX())  
        {
            System.out.println("enter ===");
            int x_direction = e.getX() - e.get_playerX();
            if(x_direction > 0)
            {
                x_direction = 1;
            }else{
                x_direction = -1;
            }
            int new_x = e.getX() + x_direction;
            if(e.enemyMovable(new_x, e.getY())) //  if x is diff ,move in x best direction 
            {
                System.out.println("herere");
                e.x().set(new_x);
                return;
            }else if(e.getY() != e.get_playerY()) // if x direction is not moveable, but y direction also diff, move in the best y direction
            {
                int y_direction = e.getY() - e.get_playerY();
                if(y_direction > 0)
                {
                    y_direction = 1;
                }else{
                    y_direction = -1;
                }
                int new_y = e.getY() + y_direction;
                if(e.enemyMovable(e.getX(),new_y )) // if best y direction is movebele
                {
                    e.y().set(new_y);
                    return;
                }
                // if function is not terminate , means must move in y direction
                int bad_y = e.getY() - y_direction;
                if(e.enemyMovable(e.getX(),bad_y )) // if bad y direction is movebele
                {
                    e.y().set(bad_y);
                    return;
                }


            }
            //if function is not terminated , means must move in bad x direction
            int bad_x = e.getX() - x_direction;
            if(e.enemyMovable(bad_x,e.getY() )) // if bad y direction is movebele
            {
                e.x().set(bad_x);
                return;
            }
            
        }else if( e.getY() != e.get_playerY()) // if y is diff, try move in best y
        {
            System.out.println("enter ===");
            
            int y_direction = e.getY() - e.get_playerY();
            if(y_direction > 0)
            {
                y_direction = 1;
            }else{
                y_direction = -1;
            }
            int new_y = e.getY() + y_direction;
            // System.out.println("e y is "+e.getY()+" player y is "+e.get_playerY()+" y_direction is "+y_direction+" new_y is "+ new_y+"\n");
            if(e.enemyMovable(e.getX(),new_y )) // if best y direction is movebele
            {
                // System.out.print("here\n");
                
                e.y().set(new_y);
                return;
                
            }else if( e.getX() != e.get_playerX()) // if best y is not moveable , and x is diff, move in best x 
            {
                int x_direction = e.getX() - e.get_playerX();
                if(x_direction > 0)
                {
                    x_direction = 1;
                }else{
                    x_direction = -1;
                }
                int new_x = e.getX() + x_direction;
                if(e.enemyMovable(new_x, e.getY())) // if best x is moveable
                {
                    // e.setX(new_x);
                    e.x().set(new_x);
                    return;
                }

                // if best x is not moveable , move in bad x
                int bad_x =e.getX() - x_direction;
                if(e.enemyMovable(bad_x, e.getY()))
                {
                    // e.setX(bad_x);
                    e.x().set(bad_x);
                    return;
                }



            }
            //if function is not terminated must move in bad y
            int bad_y = e.getY() - y_direction;
            if(e.enemyMovable(e.getX(), bad_y))
            {
                // e.setY(bad_y);
                e.y().set(bad_y);
            }


        }
    }


}