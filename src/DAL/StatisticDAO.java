package DAL;

import App.View.Shop.loadData;
import Entity.*;
import java.sql.*;
import java.util.ArrayList;

public class StatisticDAO {
    private Database statisticDAO = new Database();

    public ArrayList<StatisticProduct> getProductStatistic(Date start, Date end) {
        ArrayList<StatisticProduct> list = new ArrayList<>();
        try {
            PreparedStatement preStmt = statisticDAO.getPreStmt("""
                  select  od.ProductID, p.ProductName, sum(od.Quantity), sum(ps.ProductPrice * od.Quantity)
                  from OrderDetail od join Orders o on od.OrderID = o.OrderId
                    join ProductSize ps on ps.ProductID = od.ProductID and ps.Sizes = od.Sizes
                    join Product p on p.ProductId = od.ProductID
                  where o.OrderDate >= ? and o.OrderDate <= ?
                  group by od.ProductID, p.ProductName;""");
            preStmt.setDate(1, start);
            preStmt.setDate(2, end);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                list.add(new StatisticProduct(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for(StatisticProduct productModel : list) {
            try {
                PreparedStatement preStmt = statisticDAO.getPreStmt("""
                     select od.Sizes, sum(od.Quantity)
                     from OrderDetail od join ProductSize ps on od.ProductID = ps.ProductID and ps .Sizes = od.Sizes
                        join Orders o on od.OrderID = o.OrderId
                     where o.OrderDate >= ? and o.OrderDate <= ? and od.ProductID = ?
                     group by od.Sizes;""");
                preStmt.setDate(1, start);
                preStmt.setDate(2, end);
                preStmt.setInt(3, productModel.getProductId());
                ResultSet rs = preStmt.executeQuery();
                while(rs.next()) {
                    productModel.addProductCountBySize(
                            rs.getString(1),
                            rs.getInt(2)
                    );
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return list;
    }

//    public ArrayList<Order> getOrderStatistic(Date start, Date end) {
//        ArrayList<Order> orders = new ArrayList<>();
//        try {
//            PreparedStatement preStmt = statisticDAO.getPreStmt("""
//                select *
//                from Orders o
//                where o.OrderDate >= ? and o.OrderDate <= ?;"""
//            );
//            preStmt.setDate(1, start);
//            preStmt.setDate(2, end);
//            ResultSet rs = preStmt.executeQuery();
//            while(rs.next()) {
//                orders.add(new Order(
//                        rs.getInt(1),
//                        rs.getInt(2),
//                        rs.getDate(3),
//                        loadData.management.getCustomerManagement().findByPhone(rs.getString(4)),
//                        loadData.management.getEmployeeManagement().getEmployeeById(rs.getInt(5))
//                ));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        PreparedStatement prSt = statisticDAO.getPreStmt("select * from OrderDetail where OrderID=?");
//        for(Order order : orders){
//            ArrayList<OrderDetail> orderDetails = new ArrayList<>();
//            try {
//                prSt.setInt(1,order.getOrderId());
//                ResultSet resultSet = prSt.executeQuery();
//                while (resultSet.next()){
//                    orderDetails.add(new OrderDetail(
//                            loadData.management.getProductManagement().findById(resultSet.getInt(2)),
//                            resultSet.getString(3),
//                            resultSet.getInt(4)
//                    ));
//                }
//                order.setDetails(orderDetails);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//        return orders;
//    }

    public ArrayList<StatisticCategory> getCategoryStatistic(Date start, Date end) {
        ArrayList<StatisticCategory> list = new ArrayList<>();
        PreparedStatement preStmt = statisticDAO.getPreStmt("""
                select p.CategoryId , c.CategoryName, sum(od.Quantity), sum(od.Quantity * ps.ProductPrice)
                from OrderDetail od
                    join Orders o on od.OrderID = o.OrderID
                    join ProductSize ps on ps.ProductID = od.ProductID and ps.Sizes = od.Sizes
                    join Product p on p.ProductId = od.ProductID
                    join Category c on c.CategoryId  = p.CategoryId
                where o.OrderDate >= ? and o.OrderDate <= ?
                group by p.CategoryId, c.CategoryName;""");

        try {
            preStmt.setDate(1, start);
            preStmt.setDate(2, end);
            ResultSet rs = preStmt.executeQuery();
            while(rs.next()) {
                list.add(new StatisticCategory(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public ArrayList<RevenueMonthYear> getRevenueMonthYear(Date start, Date end) {
        ArrayList<RevenueMonthYear> list = new ArrayList<>();

        try {
            PreparedStatement preStmt = statisticDAO.getPreStmt("""
                select MONTH(o.OrderDate), YEAR(o.OrderDate), sum(o.TotalPrice), count(o.OrderId)
                from Orders o
                where o.OrderDate >= ? and o.OrderDate <= ?
                group by YEAR(o.OrderDate), MONTH(o.OrderDate);""");
            preStmt.setDate(1, start);
            preStmt.setDate(2, end);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()){
                list.add(new RevenueMonthYear(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public ArrayList<Integer> getTotal(Date from, Date to) {
        ArrayList<Integer> total = new ArrayList<>();
        try {
            PreparedStatement preStmt = statisticDAO.getPreStmt("""
                    select sum(o.TotalPrice)
                    from  Orders o
                    where o.OrderDate >= ? and o.OrderDate <= ?;""");
            preStmt.setDate(1, from);
            preStmt.setDate(2, to);
            ResultSet rs = preStmt.executeQuery();
            while(rs.next()) {
                total.add(rs.getInt(1));
            }

            preStmt = statisticDAO.getPreStmt("""
                    select sum(od.Quantity)
                    from OrderDetail od join Orders o on od.OrderID  = o.OrderId
                    where o.OrderDate >= ? and o.OrderDate <= ?;""");
            preStmt.setDate(1, from);
            preStmt.setDate(2, to);
            rs = preStmt.executeQuery();
            while(rs.next()) {
                total.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return total;
    }

}
